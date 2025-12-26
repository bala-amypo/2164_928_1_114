package com.example.demo.service.serviceimpl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenStatus;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Called by TokenController.updateStatus(id, status)
    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        TokenStatus tokenStatus = TokenStatus.valueOf(status.toUpperCase());
        token.setStatus(tokenStatus);

        if (tokenStatus == TokenStatus.COMPLETED) {
            token.setCompletedAt(LocalDateTime.now());
        }

        return tokenRepository.save(token);
    }

    // Called by TokenController.getToken(id)
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
