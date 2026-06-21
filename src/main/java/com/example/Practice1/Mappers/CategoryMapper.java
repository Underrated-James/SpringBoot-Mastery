package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Request.CategoryRequestDto;
import com.example.Practice1.Dtos.Response.CategoryDto;
import com.example.Practice1.Entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "num_of_products", expression = "java(String.valueOf(category.getProducts() == null ? 0 : category.getProducts().size()))")
    CategoryDto toDto(Category category);

    Category toEntity(CategoryRequestDto request);

    void update(CategoryRequestDto request, @MappingTarget Category category);
}
