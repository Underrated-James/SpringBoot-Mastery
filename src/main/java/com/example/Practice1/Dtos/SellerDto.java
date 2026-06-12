package com.example.Practice1.Dtos;

import com.example.Practice1.Entities.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class SellerDto {
    @Getter private Long id;
    @Getter private String name;
    @Getter private String storeName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    @Getter private LocalDateTime createdAt;
    @Getter private List<Product> products;

}
