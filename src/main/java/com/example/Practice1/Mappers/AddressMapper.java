package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Response.AddressDto;
import com.example.Practice1.Entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "user.id", target = "userId")
    AddressDto toDto(Address address);
}
