package com.saasestate.app.component.parser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasestate.app.entity.Currency;
import com.saasestate.app.core.validation.EnumValidator;
import lombok.Data;
import lombok.SneakyThrows;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

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
    public Price price;

    @NotNull
    public Location location;

    @Data
    public static class Location{

        @NotNull
        public Double lat;

        @NotNull
        public Double lng;
    }

    @Data
    public static class Price{

        @NotNull
        @Min(0)
        @Max(2147483647)
        public Integer amount;

        @EnumValidator(enumClazz = Currency.CurrencyType.class, message = "Currency type is invalid")
        @NotNull
        public String currency;
    }

    @SneakyThrows
    public String toString(){
        return (new ObjectMapper()).writeValueAsString(this);
    }
}
