package com.example.Practice1.Services;

import com.example.Practice1.Dtos.Request.TicketRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.TicketUpdateDto;
import com.example.Practice1.Dtos.Response.TicketDto;

import java.util.List;


public interface TicketService {

    List<TicketDto> getAllTickets(String sort);

    TicketDto getTicketById(Long id);

    TicketDto createTicket(TicketRequestDto request);

    TicketDto updateTicket(Long id, TicketUpdateDto request);

    TicketDto deleteTicket(Long id);
}
