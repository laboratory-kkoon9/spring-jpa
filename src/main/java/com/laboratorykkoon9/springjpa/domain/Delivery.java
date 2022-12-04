package com.laboratorykkoon9.springjpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Embedded
    private Address address;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Delivery(Long id, Order order, Address address, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
