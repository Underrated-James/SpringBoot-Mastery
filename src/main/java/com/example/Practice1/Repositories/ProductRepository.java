package com.example.Practice1.Repositories;


import com.example.Practice1.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}