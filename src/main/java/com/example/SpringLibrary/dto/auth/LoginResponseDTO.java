package com.example.SpringLibrary.dto.auth;

public class LoginResponseDTO {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    private String token;
}
