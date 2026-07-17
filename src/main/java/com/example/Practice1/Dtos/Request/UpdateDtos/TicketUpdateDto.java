package com.example.Practice1.Dtos.Request.UpdateDtos;

import com.example.Practice1.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDto {
    private String description;
}

