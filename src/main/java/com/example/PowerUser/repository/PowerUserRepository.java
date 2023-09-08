package com.example.PowerUser.repository;

import com.example.PowerUser.model.PowerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PowerUserRepository extends JpaRepository<PowerUser, Integer> {

    Optional<PowerUser> findByEmail(String email);
    Optional<PowerUser> findByFullName(String fullName);

}
