package com.laboratorykkoon9.springjpa.domain;

import lombok.*;

@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    @Builder
    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}
