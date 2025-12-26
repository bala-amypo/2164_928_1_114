package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long expiryMillis;

    // REQUIRED by tests
    public JwtTokenProvider(String secret, int expiryMinutes) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiryMillis = expiryMinutes * 60L * 1000L;
    }

    // REQUIRED by Spring (default constructor)
    public JwtTokenProvider() {
        String defaultSecret = "default-secret-key-default-secret-key";
        this.key = Keys.hmacShaKeyFor(defaultSecret.getBytes());
        this.expiryMillis = 60 * 60 * 1000L;
    }

    // REQUIRED by tests
    public String generateToken(long userId, String username, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiryMillis);

        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // REQUIRED by tests
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // REQUIRED by tests
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
