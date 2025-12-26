package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey = "my-secret-key-my-secret-key-my-secret-key";
    private long validityInMs = 3600000; // 1 hour

    private Key key;

    // ✅ REQUIRED by tests
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ✅ REQUIRED by tests
    public JwtTokenProvider(String secretKey, int validityInMs) {
        this.secretKey = secretKey;
        this.validityInMs = validityInMs;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // ✅ REQUIRED by tests
    public String generateToken(long userId, String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // (Optional backward compatibility)
    public String generateToken(String username) {
        return generateToken(0L, username, "USER");
    }

    // ✅ REQUIRED by tests
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ REQUIRED by tests
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
