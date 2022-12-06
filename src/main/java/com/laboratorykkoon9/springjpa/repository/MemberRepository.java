package com.laboratorykkoon9.springjpa.repository;

import com.laboratorykkoon9.springjpa.domain.*;
import lombok.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name" +
                        "= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
