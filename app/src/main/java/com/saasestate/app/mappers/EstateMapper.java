package com.saasestate.app.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasestate.app.entity.Currency;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.component.parser.dto.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EstateMapper {

    @Mapping(target = "data", expression = "java(item)")
    public abstract Estate toEntity(Item item) throws JsonProcessingException;

    protected Currency toCurrency(String currency) {
        return new Currency(currency);
    }

}
