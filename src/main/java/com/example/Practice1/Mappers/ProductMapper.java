package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.ProductDto;
import com.example.Practice1.Entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProductDto toDto(Product product);
}
