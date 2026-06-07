package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ProfileDto {
    private long id;
    private String bio, habbits;
    private LocalDate dateOfBirth;
    private int loyaltyPoints;
    private Long userId;
}

