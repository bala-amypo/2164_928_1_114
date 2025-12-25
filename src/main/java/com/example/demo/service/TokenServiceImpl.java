package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository t, ServiceCounterRepository c,
                            TokenLogRepository l, QueuePositionRepository q) {
        tokenRepo = t;
        counterRepo = c;
        logRepo = l;
        queueRepo = q;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter sc = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!sc.getIsActive()) {
            throw new IllegalArgumentException("Counter not active");
        }

        Token token = new Token();
        token.setServiceCounter(sc);
        token.setStatus("WAITING");
        token.setTokenNumber(sc.getCounterName() + "-" + System.currentTimeMillis());
        token = tokenRepo.save(token);

        List<Token> waiting = tokenRepo
                .findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(waiting.size());
        queueRepo.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage("ISSUED");
        logRepo.save(log);

        return token;
    }

    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        if ("WAITING".equals(token.getStatus()) && "COMPLETED".equals(status)) {
            throw new IllegalArgumentException("Invalid status");
        }

        token.setStatus(status);
        token.setCompletedAt(LocalDateTime.now());
        tokenRepo.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage(status);
        logRepo.save(log);

        return token;
    }

    public Token getToken(Long id) {
        return tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
