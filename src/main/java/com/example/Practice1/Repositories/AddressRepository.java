package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}