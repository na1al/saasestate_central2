package com.saasestate.app.mappers;

import com.saasestate.app.entity.Currency;
import com.saasestate.app.entity.Estate;
import com.saasestate.app.component.parser.dto.Item;
import org.mapstruct.Mapper;

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
