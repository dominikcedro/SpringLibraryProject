package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.UserDTO;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(Math.toIntExact(id));
    }
}