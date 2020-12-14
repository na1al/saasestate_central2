package com.saasestate.app.component.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saasestate.app.entity.Currency;
import com.saasestate.app.core.validation.EnumValidator;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @NotNull
    @Min(1)
    @Max(2147483647)
    @JsonProperty("user_id")
    public int userId;

    @NotNull
    @Min(1)
    @Max(2147483647)
    @JsonProperty("object_id")
    public int objectId;

    @NotNull
    @Min(0)
    @Max(2147483647)
    public Integer price;

    @EnumValidator(enumClazz = Currency.CurrencyType.class, message = "Currency type is invalid")
    @NotNull
    public String currency;

    @NotNull
    public Location location;

    @Data
    private static class Location{

        @NotNull
        private Double lat;

        @NotNull
        private Double lng;
    }
}
