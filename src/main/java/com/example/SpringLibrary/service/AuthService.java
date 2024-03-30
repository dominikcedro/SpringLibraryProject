package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.auth.RegisterDTO;
import com.example.SpringLibrary.dto.auth.RegisterResponseDTO;
import com.example.SpringLibrary.entity.Auth;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.repository.AuthRepository;
import com.example.SpringLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }
    public RegisterResponseDTO register(RegisterDTO dto){
        User userEntity = new User();
        userEntity.setEmail(dto.getEmail());
        User createdUser = userRepository.save(userEntity);

        Auth authEntity = new Auth();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(dto.getPassword());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

        Auth createdAuth = authRepository.save(authEntity);
        return new RegisterResponseDTO(createdAuth.getUsername(), createdAuth.getRole(), createdUser.getEmail());
    }
}
