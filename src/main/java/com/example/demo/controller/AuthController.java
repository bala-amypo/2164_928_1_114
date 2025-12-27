package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/token")
    public String token(@RequestParam String username) {
        return jwtTokenProvider.generateToken(1L, username, "USER");
    }
}
  