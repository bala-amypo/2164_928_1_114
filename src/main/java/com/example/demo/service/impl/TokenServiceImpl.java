package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;   // ✅ THIS IMPORT WAS MISSING

import java.time.LocalDateTime;
import java.util.List;

@Service
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
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!counter.getIsActive()) {
            throw new IllegalArgumentException("not active");
        }

        Token token = new Token();
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());

        Token saved = tokenRepository.save(token);

        QueuePosition qp = new QueuePosition();
        qp.setToken(saved);
        qp.setPosition(
                tokenRepository
                        .findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING")
                        .size()
        );
        queueRepository.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(saved);
        log.setLogMessage("Token issued");
        logRepository.save(log);

        return saved;
    }

    @Override
    public Token updateStatus(Long tokenId, String status) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        String current = token.getStatus();

        if (current.equals("WAITING") && status.equals("SERVING") ||
            current.equals("SERVING") && (status.equals("COMPLETED") || status.equals("CANCELLED")) ||
            current.equals("WAITING") && status.equals("CANCELLED")) {

            token.setStatus(status);

            if (status.equals("COMPLETED") || status.equals("CANCELLED")) {
                token.setCompletedAt(LocalDateTime.now());
            }

            Token saved = tokenRepository.save(token);

            TokenLog log = new TokenLog();
            log.setToken(saved);
            log.setLogMessage("Status updated");
            logRepository.save(log);

            return saved;
        }

        throw new IllegalArgumentException("Invalid status");
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
