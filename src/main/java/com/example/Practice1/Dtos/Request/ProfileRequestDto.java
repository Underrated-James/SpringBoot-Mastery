package com.example.Practice1.Dtos.Request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequestDto {
    @JsonProperty("profile_id")
    private long id;
    private String bio, habbits;
    private LocalDate dateOfBirth;
    private int loyaltyPoints;
    private Long userId;
}
