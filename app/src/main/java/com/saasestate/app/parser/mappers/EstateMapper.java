package com.saasestate.app.parser.mappers;

import com.saasestate.app.entity.Currency;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.parser.dto.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EstateMapper {

//    @Mapping(target = "address", source = "point")
//    @Mapping(target = "tags", source = "tags")
//    @Mapping(target = "params", source = "params")
    public abstract Estate toEntity(Item item);

    protected Currency toCurrency(String currency) {
        return new Currency(currency);
    }

}
