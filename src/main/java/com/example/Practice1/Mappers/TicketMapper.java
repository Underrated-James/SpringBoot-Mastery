package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "id", target = "id")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    TicketDto toDto(Ticket ticket);
}
