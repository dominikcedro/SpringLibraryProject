package com.example.SpringLibrary.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtVerifier {

    public static void main(String[] args) {
        String token = "  ";

        Key key = Keys.hmacShaKeyFor("1d+6hDh4bvD29GLzOKnShv9tvaVBvLf+LjVAEI1tGMk=".getBytes());

        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        System.out.println("Subject: " + jws.getBody().getSubject());
        System.out.println("Expiration: " + jws.getBody().getExpiration());
    }
}