package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository t, ServiceCounterRepository c, TokenLogRepository l, QueuePositionRepository q) {
        this.tokenRepo = t;
        this.counterRepo = c;
        this.logRepo = l;
        this.queueRepo = q;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter c = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!c.getIsActive()) throw new IllegalArgumentException("not active");

        Token t = new Token();
        t.setTokenNumber(UUID.randomUUID().toString());
        t.setStatus("WAITING");
        t.setIssuedAt(LocalDateTime.now());
        t.setServiceCounter(c);

        Token saved = tokenRepo.save(t);

        QueuePosition qp = new QueuePosition();
        qp.setToken(saved);
        qp.setPosition(1);
        queueRepo.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(saved);
        log.setLogMessage("CREATED");
        logRepo.save(log);

        return saved;
    }

    public Token updateStatus(Long id, String status) {
        Token t = tokenRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        if (status.equals("COMPLETED") && !t.getStatus().equals("SERVING"))
            throw new IllegalArgumentException("Invalid status");

        t.setStatus(status);
        if (status.equals("COMPLETED") || status.equals("CANCELLED"))
            t.setCompletedAt(LocalDateTime.now());

        tokenRepo.save(t);

        TokenLog log = new TokenLog();
        log.setToken(t);
        log.setLogMessage(status);
        logRepo.save(log);

        return t;
    }

    public Token getToken(Long id) {
        return tokenRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
