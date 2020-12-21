package com.saasestate.app.entity;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList="estate_id, type, amount", name = "idx_price_etau", unique = true)})
public class EstatePrices extends BaseEntity {

    public static final String TYPE_ORIGIN  = "origin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(length = 10)
    public String type;

    @Basic(optional = false)
    public int amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public Currency currency;

    public EstatePrices(){
        super();
    }

    public EstatePrices(int amount, Currency currency){
        super();
        this.amount = amount;
        this.currency = currency;
        this.type = TYPE_ORIGIN;
    }

}
