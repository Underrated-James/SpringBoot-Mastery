package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.Address;
import com.example.Practice1.Entities.Product;
import com.example.Practice1.Entities.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @JsonProperty("user_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private Profile profile;
    private List<Address> addresses;

    private Set<Product> favoriteProducts;

    @JsonIgnore
    public Set<Product> getFavoriteProducts(){
        return this.favoriteProducts;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
