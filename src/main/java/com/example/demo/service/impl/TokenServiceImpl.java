package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    // 🚨 constructor order EXACT
    public TokenServiceImpl(
            TokenRepository tokenRepo,
            ServiceCounterRepository counterRepo,
            TokenLogRepository logRepo,
            QueuePositionRepository queueRepo
    ) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
        this.logRepo = logRepo;
        this.queueRepo = queueRepo;
    }

    @Override
    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!Boolean.TRUE.equals(counter.getIsActive())) {
            throw new IllegalArgumentException("not active");
        }

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(counter.getCounterName() + "-" + UUID.randomUUID());

        token = tokenRepo.save(token);

        List<Token> waiting =
                tokenRepo.findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING");

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(waiting.size());
        qp.setUpdatedAt(LocalDateTime.now());
        queueRepo.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Token issued");
        logRepo.save(log);

        return token;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        String current = token.getStatus();

        if ("WAITING".equals(current) && "SERVING".equals(status)) {
            token.setStatus("SERVING");
        } else if ("SERVING".equals(current) && "COMPLETED".equals(status)) {
            token.setStatus("COMPLETED");
            token.setCompletedAt(LocalDateTime.now());
        } else if ("WAITING".equals(current) && "CANCELLED".equals(status)) {
            token.setStatus("CANCELLED");
            token.setCompletedAt(LocalDateTime.now());
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        token = tokenRepo.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Status changed to " + status);
        logRepo.save(log);

        return token;
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
