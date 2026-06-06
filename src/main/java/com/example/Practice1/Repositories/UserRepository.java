package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}