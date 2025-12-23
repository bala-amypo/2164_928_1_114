package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.TokenNumberGenerator;

/*
 * Token Service
 */
@Service
public class TokenService {

    private final ServiceCounterRepository counterRepo;
    private final TokenRepository tokenRepo;
    private final QueuePositionRepository queueRepo;
    private final TokenLogRepository logRepo;

    public TokenService(ServiceCounterRepository counterRepo,
                        TokenRepository tokenRepo,
                        QueuePositionRepository queueRepo,
                        TokenLogRepository logRepo) {
        this.counterRepo = counterRepo;
        this.tokenRepo = tokenRepo;
        this.queueRepo = queueRepo;
        this.logRepo = logRepo;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter counter = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!counter.isActive()) {
            throw new RuntimeException("not active");
        }

        Token token = new Token();
        token.setTokenNumber(TokenNumberGenerator.generate());
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());

        tokenRepo.save(token);

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(1);
        qp.setUpdatedAt(LocalDateTime.now());
        queueRepo.save(qp);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage("Token issued");
        log.setLoggedAt(LocalDateTime.now());
        logRepo.save(log);

        return token;
    }
}
