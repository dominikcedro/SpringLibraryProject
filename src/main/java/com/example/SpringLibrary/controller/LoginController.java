package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.LoginForm;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@RestController
public class LoginController {

    @Value("${jwt.token}")
    private String key;

    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    public LoginController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        logger.info("Received login request for user: {}", loginForm.getUsername());
        User user = userRepository.findByUsername(loginForm.getUsername());
        if (user == null) {
            logger.warn("User not found: {}", loginForm.getUsername());
            return new ResponseEntity<>("User not found!", HttpStatus.UNAUTHORIZED);
        }
        boolean ifTheSame = passwordEncoder.matches(loginForm.getPassword(), user.getPassword());
        if (!ifTheSame) {
            logger.warn("Incorrect password for user: {}", loginForm.getUsername());
            return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
        }
        long millis = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getUserID())
                .claim("role", user.getRole())
                .setIssuedAt(new Date(millis))
                .setExpiration(new Date(millis + 5 * 60 * 1000)) // 5 minutes
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        logger.info("Successfully authenticated user: {}", loginForm.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
