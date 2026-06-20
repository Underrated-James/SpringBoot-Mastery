package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Request.SellerRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.SellerUpdateDto;
import com.example.Practice1.Dtos.Response.SellerDto;
import com.example.Practice1.Entities.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    @Mapping(source = "id", target = "id")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    SellerDto toDto(Seller seller);

    Seller toEntity(SellerRequestDto request);

    void update(SellerUpdateDto request, @MappingTarget Seller seller);
}
