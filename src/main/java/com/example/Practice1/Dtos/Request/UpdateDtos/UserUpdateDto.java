package com.example.Practice1.Dtos.Request.UpdateDtos;


import lombok.Data;

@Data
public class UserUpdateDto {
    private String name;
    private String password;
    private String email;
}
