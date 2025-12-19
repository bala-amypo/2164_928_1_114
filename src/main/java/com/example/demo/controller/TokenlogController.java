package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenlogController {

    @GetMapping("/token-log")
    public String getTokenLog() {
        return "Token Log Controller Working";
    }
}
