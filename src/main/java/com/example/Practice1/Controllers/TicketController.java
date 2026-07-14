package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Mappers.TicketMapper;
import com.example.Practice1.Repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;


    @GetMapping
    public Iterable<TicketDto> getAllTickets(
            @RequestParam(required = false, defaultValue = "")String sort
    ){
        if(!Set.of("ticketNumber", "description").contains(sort)){
            sort = "ticketNumber";
        }

        return ticketRepository.findAll(Sort.by(sort))
                .stream()
                .map(ticketMapper::toDto)
                .toList();
    }
}
