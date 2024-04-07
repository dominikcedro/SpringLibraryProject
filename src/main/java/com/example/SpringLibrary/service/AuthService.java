package com.example.SpringLibrary.service;

import com.example.SpringLibrary.Role;
import com.example.SpringLibrary.dto.auth.LoginDTO;
import com.example.SpringLibrary.dto.auth.LoginResponseDTO;
import com.example.SpringLibrary.dto.auth.RegisterDTO;
import com.example.SpringLibrary.dto.auth.RegisterResponseDTO;
import com.example.SpringLibrary.entity.Auth;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.exception.auth.*;
import com.example.SpringLibrary.repository.AuthRepository;
import com.example.SpringLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        // Get the role of the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Auth currentAuth = authRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentPrincipalName));
        Role currentRole = currentAuth.getRole();

        if (currentRole == Role.ROLE_MOD && dto.getRole() == Role.ROLE_ADMIN) {
            throw ModCreateAdminException.create(currentRole.toString(), dto.getRole().toString());
        }
        Optional<Auth> existingAuth = authRepository.findByUsername(dto.getUsername());
        if(existingAuth.isPresent()){
            throw UserAlreadyExistsException.create(dto.getUsername());
        }
        // check if user with email exists
        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        if(existingUser.isPresent()){
            throw EmailAlreadyExistingException.create(dto.getEmail());
        }
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
        // check for existing user
        Optional<Auth> existingAuth = authRepository.findByUsername(dto.getUsername());
        if(existingAuth.isEmpty()){
            throw UserNotExistingException.create(dto.getUsername());
        }

        Auth auth = authRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        // check for correct password
        if(!passwordEncoder.matches(dto.getPassword(), auth.getPassword())){
            throw IncorrectPasswordException.create();
        } else {
            System.out.println("Login successful");
        }
        String token = jwtService.generateToken(auth);
        Role userRole = jwtService.getUserRole(token);
        return new LoginResponseDTO(token);
    }
}
