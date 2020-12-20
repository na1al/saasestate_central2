package com.saasestate.app.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList="userId, objectId", name = "idx_uoid", unique = true)})
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Estate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Setter
    @Getter
    @Basic(optional = false)
    public int userId;

    @Setter
    @Getter
    @Basic(optional = false)
    public int objectId;

    @Setter
    @Getter
    @ManyToOne
    public Address address;

    @Setter
    @Getter
    @Basic(optional = false)
    public int price;

    @Setter
    @Getter
    @ManyToOne(optional = false)
    public Currency currency;

}
