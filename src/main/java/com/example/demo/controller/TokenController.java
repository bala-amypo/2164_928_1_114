package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.service.impl.TokenServiceImpl;
import com.example.demo.service.impl.TokenLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private TokenLogServiceImpl tokenLogService;

    // Issue a new token for a specific counter
    @PostMapping("/issue/{counterId
