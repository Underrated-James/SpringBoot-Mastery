package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.Address;
import com.example.Practice1.Entities.Product;
import com.example.Practice1.Entities.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor

public class UserDto {
    @JsonProperty("user_id")
    @Getter private Long id;
    @Getter private String name;
    @Getter private String email;
    @Getter private Profile profile;
    @Getter private List<Address> address;

    private Set<Product> favorites;

    @JsonIgnore
    public Set<Product> getFavorites(){
        return this.favorites;
    }
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Getter private LocalDateTime createdAt;
}
