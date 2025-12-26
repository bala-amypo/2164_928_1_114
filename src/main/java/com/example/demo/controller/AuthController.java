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
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        User dbUser = userService.findByEmail(user.getEmail());

        String token = jwtProvider.generateToken(
                dbUser.getId(),
                dbUser.getEmail(),
                dbUser.getRole()
        );

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
