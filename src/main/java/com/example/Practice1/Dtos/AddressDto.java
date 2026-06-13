package com.example.Practice1.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    @JsonProperty("address_id")
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private Long userId;
}
