package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}