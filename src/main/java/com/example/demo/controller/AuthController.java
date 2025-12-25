package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // --------------------------
    // Register new user
    // --------------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User created = userService.register(user);
        return ResponseEntity.ok(Map.of(
                "id", created.getId(),
                "email", created.getEmail()
        ));
    }

    // --------------------------
    // Login user and get JWT
    // --------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = userService.authen
