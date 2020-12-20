package com.saasestate.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Address extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private double lat;

    @Basic(optional = false)
    private double lng;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "address_components_addresses",
            joinColumns = { @JoinColumn(name = "address_id") },
            inverseJoinColumns = { @JoinColumn(name = "component_id") })
    private Set<AddressComponents> components = new HashSet<>();

}
