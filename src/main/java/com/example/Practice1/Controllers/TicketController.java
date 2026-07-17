package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.Request.TicketRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.TicketUpdateDto;
import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Entities.Ticket;
import com.example.Practice1.Entities.User;
import com.example.Practice1.Mappers.TicketMapper;
import com.example.Practice1.Repositories.TicketRepository;
import com.example.Practice1.Repositories.UserRepository;
import com.example.Practice1.Response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    //binds
    private final UserRepository userRepository;


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


    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(
            @PathVariable long id
    ){
        var ticket = ticketRepository.findById(id).orElse(null);

        if(ticket == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ticketMapper.toDto(ticket));

    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(
            @RequestBody TicketRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
    ){
        User user = userRepository.findById(request.getTicketOwnerId()).orElseThrow( () -> new RuntimeException("User not found"));
        System.out.println(request.getTicketNumber());
        Ticket ticket = ticketMapper.toEntity(request);
        System.out.println(ticket.getTicketNumber());
        ticket.setTicketOwner(user);
        ticketRepository.save(ticket);

        var ticketDto = ticketMapper.toDto(ticket);

        var uri = uriComponentsBuilder.path("/tickets/{id}").buildAndExpand(ticketDto.getId()).toUri();

        return ResponseEntity.created(uri).body(ticketDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(
            @PathVariable(name = "id")Long id,
            @RequestBody TicketUpdateDto request
            ){
        var ticket = ticketRepository.findById(id).orElse(null);

        if(ticket == null){
            return ResponseEntity.notFound().build();
        }

        ticketMapper.update(request, ticket);

        ticketRepository.save(ticket);

        return ResponseEntity.ok(ticketMapper.toDto(ticket));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> deleteTicket (
            @PathVariable(name = "id")Long id
    ){
        var ticket = ticketRepository.findById(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Ticket not Found"));
        TicketDto response = ticketMapper.toDto(ticket);
        ticketRepository.delete(ticket);

        return ResponseEntity.ok(ApiResponse.<TicketDto>builder()
                .status(HttpStatus.OK.value())
                .message("Ticket Deleted SuccessFully")
                .timestamp(LocalDateTime.now())
                .data(ticketMapper.toDto(ticket))
                .build());

    }
}
