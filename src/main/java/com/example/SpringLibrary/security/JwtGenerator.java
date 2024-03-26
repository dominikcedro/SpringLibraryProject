package com.example.SpringLibrary.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

public class JwtGenerator {
    private static final Logger logger = LoggerFactory.getLogger(JwtGenerator.class);

    public static void main(String[] args) {
        String userId = "1"; // replace with the ID of the authenticated user
        String userRole = "ROLE_ADMIN"; // replace with the role of the authenticated user

        Key key = Keys.hmacShaKeyFor(JWTTokenFilter.SECRET_KEY.getBytes());

        String jws = Jwts.builder()
                .claim("id", userId)
                .claim("role", userRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        logger.info("Generated JWT: {}", jws);
        logger.info("Secret Key: {}", JWTTokenFilter.SECRET_KEY);
        logger.info("Signature Algorithm: {}", SignatureAlgorithm.HS256);
        System.out.println(jws);
    }
}