package com.laboratorykkoon9.springjpa.service;


import com.laboratorykkoon9.springjpa.domain.*;
import com.laboratorykkoon9.springjpa.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@ActiveProfiles("test")
@ContextConfiguration(
        initializers = {ConfigFileApplicationContextInitializer.class}
)
@DataJpaTest
public class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init() {
        memberRepository = new MemberRepository(em);
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void 회원가입() {
        // given
        Member member = Member.builder()
                .name("kim")
                .build();

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        // given
        Member member1 = Member.builder()
                .name("kim")
                .build();

        Member member2 = Member.builder()
                .name("kim")
                .build();

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(
                () -> memberService.join(member2)
        ).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 존재하는 회원입니다.");
    }
}
