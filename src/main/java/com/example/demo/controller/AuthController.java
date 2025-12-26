package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.AuthResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username) {

        // ✅ Correct method call (ONLY STRING)
        String token = jwtTokenProvider.generateToken(username);

        return new AuthResponse(
                token,
                1L,
                username,
                "USER"
        );
    }
}
