package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepository;
    private final QueuePositionRepository queueRepository;

    public TokenServiceImpl(
            TokenRepository tokenRepository,
            ServiceCounterRepository counterRepository,
            TokenLogRepository logRepository,
            QueuePositionRepository queueRepository
    ) {
        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepository = logRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public Token issueToken(Long counterId) {

        ServiceCounter counter = counterRepository.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!counter.getIsActive()) {
            throw new IllegalArgumentException("not active");
        }

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(UUID.randomUUID().toString());

        token = tokenRepository.save(token);

        List<Token> waiting =
                tokenRepository.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(
                        counterId, "WAITING"
                );

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(waiting.size());
        qp.setUpdatedAt(LocalDateTime.now());
        queueRepository.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Token issued");
        logRepository.save(log);

        return token;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        String current = token.getStatus();

        boolean valid =
                (current.equals("WAITING") && (status.equals("SERVING") || status.equals("CANCELLED")))
                || (current.equals("SERVING") && (status.equals("COMPLETED") || status.equals("CANCELLED")));

        if (!valid) {
            throw new IllegalArgumentException("Invalid status");
        }

        token.setStatus(status);

        if (status.equals("COMPLETED") || status.equals("CANCELLED")) {
            token.setCompletedAt(LocalDateTime.now());
        }

        token = tokenRepository.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Status updated to " + status);
        logRepository.save(log);

        return token;
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
