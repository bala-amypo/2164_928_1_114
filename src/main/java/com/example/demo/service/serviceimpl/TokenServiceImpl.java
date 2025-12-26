package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenStatus;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl {

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

    /**
     * t15_updateTokenStatusToServing
     * t22_tokenServiceUsesRepos
     */
    public Token updateTokenStatusToServing(Token token) {
        token.setStatus(TokenStatus.SERVING);
        return tokenRepository.save(token);
    }

    /**
     * t16_updateTokenToCompletedSetsTimestamp
     */
    public Token updateTokenToCompleted(Token token) {
        token.setStatus(TokenStatus.COMPLETED);
        token.setCompletedAt(LocalDateTime.now());
        return tokenRepository.save(token);
    }

    /**
     * t69_evaluateTokenCancellation
     */
    public Token cancelToken(Token token) {
        if (token.getStatus() == TokenStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed token");
        }
        token.setStatus(TokenStatus.CANCELLED);
        return tokenRepository.save(token);
    }

    /**
     * t66_issueManyTokensSequence
     * Ensures no shared state / stateless behavior
     */
    public Token issueToken(Token token) {
        token.setStatus(TokenStatus.WAITING);
        return tokenRepository.save(token);
    }
}
