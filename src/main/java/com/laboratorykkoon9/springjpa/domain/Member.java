package com.laboratorykkoon9.springjpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 이 클래스를 테이블과 매핑한다고 JPA에 알려줌. (엔티티 클래스라고 명시해주는 어노테이션)
@Table(name = "MEMBER") // 엔티티 클래스에 매핑할 테이블 정보 이 어노테이션을 생략하면 클래스 이름을 테이블 이름으로 매핑한다. (정확히는 엔티티 이름을 사용)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id // 엔티티 클래스의 필드를 테이블의 기본 키에 매핑
    @GeneratedValue
    @Column(name = "MEMBER_ID") // 필드를 컬럼에 매핑
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(Long id, String name, Address address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }
}
