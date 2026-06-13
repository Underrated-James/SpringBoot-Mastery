package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
    @JsonProperty("profile_id")
    private long id;
    private String bio, habbits;
    private LocalDate dateOfBirth;
    private int loyaltyPoints;
    private Long userId;
}

