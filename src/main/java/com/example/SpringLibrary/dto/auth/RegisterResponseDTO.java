package com.example.SpringLibrary.dto.auth;

import com.example.SpringLibrary.Role;

public class RegisterResponseDTO {
    private String username;
    private Role role;
    private String email;

    public RegisterResponseDTO(String username, Role role, String email) {
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}