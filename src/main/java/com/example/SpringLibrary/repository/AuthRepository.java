package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long>{
    Optional<Auth> findByUsername(String username);
}
