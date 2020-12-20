package com.saasestate.app.component.geo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressComponents {

    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String[] types;
}
