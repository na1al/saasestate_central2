package com.saasestate.app.mappers;

import com.saasestate.app.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    public abstract Address toEntity(com.saasestate.app.component.geo.dto.Address item);
}
