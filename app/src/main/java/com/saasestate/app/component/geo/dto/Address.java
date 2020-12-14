package com.saasestate.app.component.geo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {

    @JsonProperty("Id")
    private long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Lat")
    private Double lat;

    @JsonProperty("Lng")
    private Double lng;

}
