package com.laboratorykkoon9.springjpa.model.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
