package com.example.Practice1.Dtos.Request;

import com.example.Practice1.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private String ticketNumber;
    private String description;
    private Long ticketOwnerId;

}

