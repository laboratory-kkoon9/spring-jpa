package com.laboratorykkoon9.springjpa.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Delivery(Long id, Order order, String city, String street, String zipcode, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.order = order;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.deliveryStatus = deliveryStatus;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
