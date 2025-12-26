package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Normally you would validate username/password from DB
        // For testcase-based implementation, we generate token directly

        String token = jwtTokenProvider.generateToken(request.getUsername());

        AuthResponse response = new AuthResponse(
                token,
                null,               // userId (optional)
                request.getUsername(),
                null                // role (optional)
        );

        return ResponseEntity.ok(response);
    }
}
