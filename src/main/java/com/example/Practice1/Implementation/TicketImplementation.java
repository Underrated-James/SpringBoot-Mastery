package com.example.Practice1.Implementation;


import com.example.Practice1.Dtos.Request.TicketRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.TicketUpdateDto;
import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Entities.Ticket;
import com.example.Practice1.Mappers.TicketMapper;
import com.example.Practice1.Repositories.TicketRepository;
import com.example.Practice1.Repositories.UserRepository;
import com.example.Practice1.Services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketImplementation implements TicketService {

    @Autowired
    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public List<TicketDto> getAllTickets(String sort) {
        String sortField = Set.of("ticketNumber", "description")
                .contains(sort) ? sort : "ticketNumber";

        return ticketRepository.findAll(Sort.by(sortField))
                .stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    @Override
    public TicketDto getTicketById(Long id) {
        var ticket = ticketRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Ticket not found"));
        return ticketMapper.toDto(ticket);

    }

    @Override
    public TicketDto createTicket(TicketRequestDto request) {
        var user = userRepository.findById(request.getTicketOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found"));
        Ticket ticket = ticketMapper.toEntity(request);
        ticket.setTicketOwner(user);
        ticketRepository.save(ticket);
        return  ticketMapper.toDto(ticket);

    }

    @Override
    public TicketDto updateTicket(Long id, TicketUpdateDto request) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        ticketMapper.update(request, ticket);
        ticketRepository.save(ticket);

        return ticketMapper.toDto(ticket);

    }

    @Override
    public TicketDto deleteTicket(Long id) {
        var ticket = ticketRepository.findById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
        TicketDto dto = ticketMapper.toDto(ticket);

        ticketRepository.delete(ticket);

        return dto;

    }
}
