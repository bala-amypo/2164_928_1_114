package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey;
    private int expirationInMs;

    // ✅ REQUIRED by tests
    public JwtTokenProvider(String secretKey, int expirationInMs) {
        this.secretKey = secretKey;
        this.expirationInMs = expirationInMs;
    }

    // ✅ REQUIRED default constructor (Spring + tests safety)
    public JwtTokenProvider() {
        this.secretKey = "defaultSecretKey";
        this.expirationInMs = 3600000;
    }

    // ✅ REQUIRED by tests
    public String generateToken(long userId, String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // ✅ REQUIRED by tests
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ REQUIRED by tests
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
