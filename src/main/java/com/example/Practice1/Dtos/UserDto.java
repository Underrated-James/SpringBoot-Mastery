package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.Address;
import com.example.Practice1.Entities.Product;
import com.example.Practice1.Entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Profile profile;
    private List<Address> address;
    private Set<Product> favorites;
}
