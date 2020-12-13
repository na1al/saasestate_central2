package com.saasestate.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(indexes = {@Index(columnList="userId, objectId", name = "idx_uoid", unique = true)})
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
