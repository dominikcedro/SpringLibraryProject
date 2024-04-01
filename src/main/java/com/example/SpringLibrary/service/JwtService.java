package com.example.SpringLibrary.service;

import com.example.SpringLibrary.Role;
import com.example.SpringLibrary.entity.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private long tokenLifeTime= 1000 * 60 * 30;

    @Value("${jwt.signing.key}")
    private String jwtsigningkey;

    public String generateToken(Auth userDetail){
    return generateToken(new HashMap<>(), userDetail);
    }

    public Role getUserRole(String token){
        String roleString = extractClaim(token, (claims) -> claims.get("role", String.class));
        return Role.valueOf(roleString);
    }

    public boolean isTokenVaild(String token){
        try{
            return verifyToken(token) && !isTokenExpired(token); // modified

        } catch (Exception e){
            return false;
        }
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T  extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private boolean verifyToken(String token){
        return true;
    }

    public String getUsernameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private String generateToken(Map<String, Object> extraClaims, Auth userDetail){
        extraClaims.put("role", userDetail.getRole());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetail.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLifeTime))
                .signWith(getSigningKey())
                .compact();

    }
    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtsigningkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
