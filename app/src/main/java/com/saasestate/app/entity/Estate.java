package com.saasestate.app.entity;

import com.saasestate.app.component.parser.dto.Item;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

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
    @Basic(optional = false)
    public int price;

    @Setter
    @Getter
    @ManyToOne(optional = false)
    public Currency currency;

    @JsonIgnore
    @Getter
    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private final List<EstateHistory> histories = new ArrayList<>();

    @Basic
    private int dataHash;

    @Column
    private Integer oldDataHash;

    @JsonIgnore
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    public Item data;

//    @Setter
//    @Getter
//    @ManyToOne(optional = false)
//    public Address address;
//
//    @Getter
//    @Setter
//    @ManyToMany(fetch = FetchType.EAGER)
//    private  Collection<Tag> tags = new HashSet<>();
//
//    @Getter
//    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private final Set<Param> params = new HashSet<>();
//
//    @Getter
//    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
//    private final Set<EstateAddressNearest> nearest = new HashSet<>();
//
//    @Getter
//    @ManyToMany(cascade = CascadeType.ALL)
//    private final Collection<FileEntity> images = new ArrayList<>();


}
