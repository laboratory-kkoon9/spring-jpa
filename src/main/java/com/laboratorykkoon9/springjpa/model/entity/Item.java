package com.laboratorykkoon9.springjpa.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STOCKQUANTITY")
    private Integer quantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Item(Long id, Integer price, Integer quantity, List<Category> categories) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.categories = categories;
    }
}
