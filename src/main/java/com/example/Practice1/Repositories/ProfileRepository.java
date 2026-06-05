package com.example.Practice1.Repositories;

import com.example.Practice1.Entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
