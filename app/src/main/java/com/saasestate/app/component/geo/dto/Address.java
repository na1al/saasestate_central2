package com.saasestate.app.component.geo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {

    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Double lat;

    @JsonProperty
    private Double lng;

}
