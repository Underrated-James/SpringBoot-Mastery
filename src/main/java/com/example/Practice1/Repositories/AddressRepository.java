package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}