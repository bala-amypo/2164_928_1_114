package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.impl.TokenServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenServiceImpl tokenService;

    public TokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/issue/{counterId}")
    public Token issue(@PathVariable Long counterId) {
        return tokenService.issueToken(counterId);
    }

    @PutMapping("/{id}/status/{status}")
    public Token update(@PathVariable Long id, @PathVariable String status) {
        return tokenService.updateStatus(id, status);
    }
}
