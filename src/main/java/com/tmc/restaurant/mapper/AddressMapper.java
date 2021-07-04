package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.AddressDto;
import com.tmc.restaurant.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toAddressDto(Address address);
    Address toAddress(AddressDto addressDto);
}
