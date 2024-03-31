package com.example.SpringLibrary.service;

import com.example.SpringLibrary.Role;
import com.example.SpringLibrary.dto.auth.LoginDTO;
import com.example.SpringLibrary.dto.auth.LoginResponseDTO;
import com.example.SpringLibrary.dto.auth.RegisterDTO;
import com.example.SpringLibrary.dto.auth.RegisterResponseDTO;
import com.example.SpringLibrary.entity.Auth;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.repository.AuthRepository;
import com.example.SpringLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    public RegisterResponseDTO register(RegisterDTO dto){
        User userEntity = new User();
        userEntity.setEmail(dto.getEmail());
        User createdUser = userRepository.save(userEntity);

        Auth authEntity = new Auth();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

        Auth createdAuth = authRepository.save(authEntity);

        return new RegisterResponseDTO(createdAuth.getUsername(), createdAuth.getRole(), createdUser.getEmail(), createdUser.getId());
    }
    public LoginResponseDTO login(LoginDTO dto){
        Auth auth = authRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(dto.getPassword(), auth.getPassword())){
            throw new RuntimeException("Invalid password");
        } else {
            System.out.println("Login successful");
        }
        String token = jwtService.generateToken(auth);
        Role userRole = jwtService.getUserRole(token);
        return new LoginResponseDTO(token);
    }
}
