package com.laboratorykkoon9.springjpa.domain.item;

import com.laboratorykkoon9.springjpa.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STOCKQUANTITY")
    private Integer quantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Item(Long id, String name, Integer price, Integer quantity, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categories = categories;
    }
}
