package com.laboratorykkoon9.springjpa.service;

import com.laboratorykkoon9.springjpa.domain.*;
import com.laboratorykkoon9.springjpa.domain.item.*;
import com.laboratorykkoon9.springjpa.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ItemService itemService;

    @Transactional
    public Long order(final Long memberId, final Long itemId, final int count) {
        Member member = this.memberRepository.findOne(memberId);
        Item item = this.itemService.findOne(itemId);

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    @Transactional
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

}
