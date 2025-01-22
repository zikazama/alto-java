package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret.key}") String secretKeyBase64) {
        System.out.println("Loaded secret key: " + secretKeyBase64); // Tambahkan log
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
            this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Base64 key format for jwt.secret.key", e);
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Berlaku 1 jam
                .signWith(secretKey) // Gunakan kunci aman
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
