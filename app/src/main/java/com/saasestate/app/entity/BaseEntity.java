package com.saasestate.app.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now() not null")
    private Date createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now() not null")
    private Date updatedAt;
}
