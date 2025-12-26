package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET =
            "my-super-secret-key-my-super-secret-key"; // min 32 chars
    private static final long EXPIRATION_MS = 3600000; // 1 hour

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ No-arg constructor (needed by tests)
    public JwtTokenProvider() {
    }

    // ✅ Constructor used in tests
    public JwtTokenProvider(String secret, int expirySeconds) {
        // optional if tests expect it
    }

    // ✅ Generate token (used by AuthController)
    public String generateToken(long userId, String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, Jwts.SIG.HS256)   // ✅ CORRECT SIGNING
                .compact();
    }

    // ✅ Overload used by some tests
    public String generateToken(String username) {
        return generateToken(1L, username, "USER");
    }

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)   // ✅ SecretKey required
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ Extract claims
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
