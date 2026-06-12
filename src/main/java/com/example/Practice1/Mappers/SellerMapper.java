package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.SellerDto;
import com.example.Practice1.Entities.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    @Mapping(source = "id", target = "id")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    SellerDto toDto(Seller seller);
}
