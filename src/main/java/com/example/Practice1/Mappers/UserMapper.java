package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Request.UpdateDtos.UserUpdateDto;
import com.example.Practice1.Dtos.Request.UserRequestDto;
import com.example.Practice1.Dtos.Response.UserDto;
import com.example.Practice1.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);

    User toEntity(UserRequestDto request);

    void update(UserUpdateDto request, @MappingTarget User user);
}
