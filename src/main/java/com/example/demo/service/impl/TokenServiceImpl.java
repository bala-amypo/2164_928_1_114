package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service   // 🔴 REQUIRED
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepository;

    public TokenServiceImpl(
            TokenRepository tokenRepository,
            ServiceCounterRepository counterRepository,
            TokenLogRepository logRepository) {

        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepository = logRepository;
    }

    @Override
    public Token issueToken(Long counterId) {
        counterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        Token token = new Token();
        token.setCounterId(counterId);
        token.setStatus("ISSUED");
        token.setIssuedAt(LocalDateTime.now());

        Token saved = tokenRepository.save(token);

        TokenLog log = new TokenLog();
        log.setTokenId(saved.getId());
        log.setMessage("Token issued");
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);

        return saved;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        token.setStatus(status);
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
