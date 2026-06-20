package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.ProfileDto;
import com.example.Practice1.Dtos.Request.ProfileRequestDto;
import com.example.Practice1.Entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(source = "user.id", target = "userId")
    ProfileDto toDto(Profile profile);

    Profile toEntity(ProfileRequestDto request);
}
