package com.saasestate.app.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class EstateHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Estate estate;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String data;


}
