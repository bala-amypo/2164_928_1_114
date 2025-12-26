package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // MUST be at least 32 characters for HS256
    private static final String SECRET_KEY =
            "my-super-secure-secret-key-123456";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final SecretKey key;

    // ✅ NO-ARGS constructor (required by Spring + tests)
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ✅ Used by tests: generateToken(username)
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Used by tests: generateToken(id, username, role)
    public String generateToken(Long id, String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("id", id)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Used by tests
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Used by tests
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
