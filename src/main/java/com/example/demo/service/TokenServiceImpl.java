package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepository;
    private final QueuePositionRepository queueRepository;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            ServiceCounterRepository counterRepository,
                            TokenLogRepository logRepository,
                            QueuePositionRepository queueRepository) {
        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepository = logRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!counter.getActive()) {
            throw new RuntimeException("not active");
        }

        Token token = new Token("T-" + System.currentTimeMillis(), counter, "WAITING");
        tokenRepository.save(token);

        queueRepository.save(new QueuePosition(token, 1));
        logRepository.save(new TokenLog(token, "Token issued"));

        return token;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!status.equals("SERVING") && !status.equals("COMPLETED")) {
            throw new RuntimeException("Invalid status");
        }

        token.setStatus(status);
        if ("COMPLETED".equals(status)) {
            token.setCompletedAt(LocalDateTime.now());
        }

        logRepository.save(new TokenLog(token, "Status changed"));
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
