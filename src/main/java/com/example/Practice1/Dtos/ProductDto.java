package com.example.Practice1.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductDto {
    private long id;
    private String name, description;
    private BigDecimal price;
    private Integer quantity;
    private long categoryId;
    private LocalDateTime createdAt;
    private Long sellerId;
}
