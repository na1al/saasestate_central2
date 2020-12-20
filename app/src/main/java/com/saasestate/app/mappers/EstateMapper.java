package com.saasestate.app.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saasestate.app.entity.Currency;
import com.saasestate.app.component.parser.dto.Item;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.entity.EstateData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EstateMapper {

    public abstract Estate toEntity(Item item) throws JsonProcessingException;

    @Mapping(target = "data", expression = "java(item)")
    public abstract EstateData toEntityData(Item item) throws JsonProcessingException;

    protected Currency toCurrency(String currency) {
        return new Currency(currency);
    }

}
