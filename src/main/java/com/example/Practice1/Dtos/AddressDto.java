package com.example.Practice1.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private Long userId;
}
