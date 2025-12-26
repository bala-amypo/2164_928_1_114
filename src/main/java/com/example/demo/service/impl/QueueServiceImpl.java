package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepo;
    private final TokenRepository tokenRepo;

    public QueueServiceImpl(QueuePositionRepository queueRepo,
                            TokenRepository tokenRepo) {
        this.queueRepo = queueRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {

        if (newPosition < 1) {
            throw new IllegalArgumentException("1");
        }

        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        QueuePosition qp = queueRepo.findByToken_Id(tokenId)
                .orElse(new QueuePosition());

        qp.setToken(token);
        qp.setPosition(newPosition);
        qp.setUpdatedAt(LocalDateTime.now());

        return queueRepo.save(qp);
    }

    @Override
    public QueuePosition getPosition(Long tokenId) {
        return queueRepo.findByToken_Id(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
