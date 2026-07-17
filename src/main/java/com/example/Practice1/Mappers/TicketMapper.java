package com.example.Practice1.Mappers;

import com.example.Practice1.Dtos.Request.TicketRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.TicketUpdateDto;
import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "ticketOwner.id", target = "ticketOwnerId")

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    TicketDto toDto(Ticket ticket);

    Ticket toEntity(TicketRequestDto request);

    void update(TicketUpdateDto request, @MappingTarget Ticket ticket);
}
