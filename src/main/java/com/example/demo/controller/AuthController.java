package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtProvider;

    public AuthController(UserService userService) {
        this.userService = userService;
        this.jwtProvider = new JwtTokenProvider(
                "mySecretKey123456789",
                3600000
        );
