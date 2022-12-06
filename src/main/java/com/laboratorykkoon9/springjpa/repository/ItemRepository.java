package com.laboratorykkoon9.springjpa.repository;

import com.laboratorykkoon9.springjpa.domain.item.*;
import lombok.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
