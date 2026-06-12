package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
