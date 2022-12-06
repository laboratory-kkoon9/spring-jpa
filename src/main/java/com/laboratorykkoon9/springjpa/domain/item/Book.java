package com.laboratorykkoon9.springjpa.domain.item;

import com.laboratorykkoon9.springjpa.domain.*;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    public Book(Long id, String name, Integer price, Integer quantity, List<Category> categories, String author, String isbn) {
        super(id, name, price, quantity, categories);
        this.author = author;
        this.isbn = isbn;
    }
}
