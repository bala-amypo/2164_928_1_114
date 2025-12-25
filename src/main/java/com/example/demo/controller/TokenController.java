package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.ServiceCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ServiceCounterRepository serviceCounterRepository;

    @GetMapping
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @PostMapping("/issue/{counterId}")
    public Token issueToken(@PathVariable Long counterId) {
        ServiceCounter counter = serviceCounterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());

        return tokenRepository.save(token);
    }

    @PostMapping("/complete/{tokenId}")
    public Token completeToken(@PathVariable Long tokenId) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        token.setStatus("COMPLETED");
        token.setCompletedAt(LocalDateTime.now());

        return tokenRepository.save(token);
    }
}
