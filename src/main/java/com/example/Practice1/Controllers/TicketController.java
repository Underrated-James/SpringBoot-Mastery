package com.example.Practice1.Controllers;

import com.example.Practice1.Dtos.Request.TicketRequestDto;
import com.example.Practice1.Dtos.Request.UpdateDtos.TicketUpdateDto;
import com.example.Practice1.Dtos.Response.TicketDto;
import com.example.Practice1.Response.ApiResponse;
import com.example.Practice1.Services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketService ticketService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketDto>>> getAllTickets(
            @RequestParam(required = false, defaultValue = "")String sort
    ){
        List<TicketDto> tickets = ticketService.getAllTickets(sort);

        ApiResponse<List<TicketDto>> response = ApiResponse.<List<TicketDto>>builder()
                .status(HttpStatus.OK.value())
                .message("All tickets retrieve successfully")
                .timestamp(LocalDateTime.now())
                .data(tickets)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> getTicketById(
            @PathVariable long id
    ){
        var ticket = ticketService.getTicketById(id);

        return ResponseEntity.ok(ApiResponse.<TicketDto>builder()
                .status(HttpStatus.OK.value())
                .message("Ticket retrieve by id")
                .timestamp(LocalDateTime.now())
                .data(ticket)
                .build());

    }

    @PostMapping
    public ResponseEntity<ApiResponse<TicketDto>> createTicket(
            @RequestBody TicketRequestDto request,
            UriComponentsBuilder uriComponentsBuilder
    ){
        TicketDto ticket = ticketService.createTicket(request);

        URI uri = uriComponentsBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();


        return ResponseEntity.created(uri)
                .body(
                        ApiResponse.<TicketDto>builder()
                                .status(HttpStatus.CREATED.value())
                                .message("Ticket created successfully")
                                .timestamp(LocalDateTime.now())
                                .data(ticket)
                                .build()
                );



    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> updateTicket(
            @PathVariable(name = "id")Long id,
            @RequestBody TicketUpdateDto request
            ){
        TicketDto ticket = ticketService.updateTicket(id, request);

        return ResponseEntity.ok(ApiResponse.<TicketDto>builder()
                .status(HttpStatus.OK.value())
                .message("Ticket Updated Successfully")
                .timestamp(LocalDateTime.now())
                .data(ticket)
                .build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TicketDto>> deleteTicket (
            @PathVariable(name = "id")Long id
    ){

        TicketDto ticket = ticketService.deleteTicket(id);


        return ResponseEntity.ok(ApiResponse.<TicketDto>builder()
                .status(HttpStatus.OK.value())
                .message("Ticket Deleted SuccessFully")
                .timestamp(LocalDateTime.now())
                .data(ticket)
                .build());

    }
}
