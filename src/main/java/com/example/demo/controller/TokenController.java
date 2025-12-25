package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.impl.TokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenServiceImpl service;

    public TokenController(TokenServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/issue/{counterId}")
    public ResponseEntity<Token> issueToken(
            @PathVariable Long counterId) {
        return ResponseEntity.ok(service.issueToken(counterId));
    }

    @PutMapping("/{tokenId}/status")
    public ResponseEntity<Token> updateStatus(
            @PathVariable Long tokenId,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(tokenId, status));
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<Token> getToken(
            @PathVariable Long tokenId) {
        return ResponseEntity.ok(service.getToken(tokenId));
    }
}
