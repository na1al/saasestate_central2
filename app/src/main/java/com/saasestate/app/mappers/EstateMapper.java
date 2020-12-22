package com.saasestate.app.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saasestate.app.entity.Currency;
import com.saasestate.app.component.parser.dto.Item;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.entity.EstateData;
import com.saasestate.app.entity.EstatePrices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class EstateMapper {

    @Mapping(target = "prices", source = "price")
    public abstract Estate toEntity(Item item) throws JsonProcessingException;

    @Mapping(target = "data", expression = "java(item)")
    public abstract EstateData toEntityData(Item item) throws JsonProcessingException;

    protected Currency toCurrency(String currency) {
        return new Currency(currency);
    }

    protected Set<EstatePrices> toPrices(Item.Price price) {
        return  new HashSet<>(Collections.singletonList(new EstatePrices(price.amount, new Currency(price.currency))));
    }

}
