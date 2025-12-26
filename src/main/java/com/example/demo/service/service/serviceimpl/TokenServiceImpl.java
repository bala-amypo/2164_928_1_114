package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenStatus;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl {

    private final TokenRepository repository;

    public TokenServiceImpl(TokenRepository repository) {
        this.repository = repository;
    }

    /**
     * Called by TokenController.issueToken(Long)
     */
    public Token issueToken(Long userId) {
        Token token = new Token();
        token.setStatus(TokenStatus.WAITING);
        return repository.save(token);
    }

    /**
     * Called by TokenController.updateStatus(Long, String)
     */
    public Token updateStatus(Long tokenId, String status) {
        Token token = repository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        TokenStatus tokenStatus = TokenStatus.valueOf(status.toUpperCase());
        token.setStatus(tokenStatus);

        if (tokenStatus == TokenStatus.COMPLETED) {
            token.setCompletedAt(LocalDateTime.now());
        }

        return repository.save(token);
    }

    /**
     * Called by TokenController.getToken(Long)
     */
    public Token getToken(Long tokenId) {
        return repository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
