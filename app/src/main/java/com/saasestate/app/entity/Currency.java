package com.saasestate.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Currency extends BaseEntity {

    @Id
    @Column(columnDefinition = "varchar(3)")
    @Enumerated(EnumType.STRING)
    public CurrencyType type;

    @NotNull
    public float rate;

    public Currency(){
        super();
    }

    public Currency(String type){
        super();
        this.type = CurrencyType.valueOf(type);
    }

    public enum CurrencyType {
        USD, UAH
    }

}