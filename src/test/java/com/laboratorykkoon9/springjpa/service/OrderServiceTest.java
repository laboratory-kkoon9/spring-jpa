package com.laboratorykkoon9.springjpa.service;

import com.laboratorykkoon9.springjpa.domain.*;
import com.laboratorykkoon9.springjpa.domain.Order;
import com.laboratorykkoon9.springjpa.domain.item.*;
import com.laboratorykkoon9.springjpa.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

import javax.persistence.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ActiveProfiles("test")
@ContextConfiguration(
        initializers = {ConfigFileApplicationContextInitializer.class}
)
@DataJpaTest
class OrderServiceTest {
    private OrderService orderService;
    private ItemService itemService;
    private MemberRepository memberRepository;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager memberEm;

    @PersistenceContext
    private EntityManager orderEm;

    @PersistenceContext
    private EntityManager itemEm;

    @BeforeEach
    void init() {
        memberRepository = new MemberRepository(memberEm);
        orderRepository = new OrderRepository(orderEm);
        itemRepository = new ItemRepository(itemEm);
        itemService = new ItemService(itemRepository);
        orderService = new OrderService(memberRepository, orderRepository, itemService);
    }

    @Test
    void 상품주문() {
        // given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertAll(
                () -> assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER),
                () -> assertThat(getOrder.getOrderItems().size()).isEqualTo(1),
                () -> assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * 2),
                () -> assertThat(item.getQuantity()).isEqualTo(8)
                );

    }

    private Member createMember() {
        Member member = Member.builder()
                .name("회원1")
                .address(new Address("서울", "강가", "123-123"))
                .orders(new ArrayList<>())
                .build();
        this.memberRepository.save(member);

        return member;
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .quantity(stockQuantity)
                .price(price)
                .build();
        this.itemRepository.save(book);

        return book;
    }

}
