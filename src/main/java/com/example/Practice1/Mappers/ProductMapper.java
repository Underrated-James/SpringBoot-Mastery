package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Response.ProductDto;
import com.example.Practice1.Entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "sellerId", expression = "java(product.getSeller() == null || product.getSeller().getId() == null ? null : product.getSeller().getId())")

    @Mapping(target = "categoryId", expression = "java(product.getCategory() == null || product.getCategory().getId() == null ? null : product.getCategory().getId().longValue())")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProductDto toDto(Product product);
}
