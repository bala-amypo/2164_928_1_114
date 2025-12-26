package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenStatus;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.ServiceCounterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository tokenRepo,
                            ServiceCounterRepository counterRepo,
                            TokenLogRepository logRepo,
                            QueuePositionRepository queueRepo) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
        this.logRepo = logRepo;
        this.queueRepo = queueRepo;
    }

    public Token updateStatusToServing(Token token) {
        token.setStatus(TokenStatus.SERVING);                   // t15
        return tokenRepo.save(token);
    }

    public Token completeToken(Token token) {
        token.setStatus(TokenStatus.COMPLETED);                 // t16
        token.setCompletedAt(LocalDateTime.now());
        return tokenRepo.save(token);
    }

    public Token cancelToken(Token token) {
        if (token.getStatus() == TokenStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed token");
        }
        token.setStatus(TokenStatus.CANCELLED);                 // t69
        return tokenRepo.save(token);
    }
}
