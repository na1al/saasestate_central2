package com.saasestate.app.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Getter
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now() not null", updatable = false)
    protected Date createdAt;

    @Getter
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now() not null")
    protected Date updatedAt;
}
