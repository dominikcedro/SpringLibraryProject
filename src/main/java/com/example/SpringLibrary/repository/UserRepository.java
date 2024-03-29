package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}