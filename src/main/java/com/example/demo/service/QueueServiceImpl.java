package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

public class QueueServiceImpl {

    private final QueuePositionRepository repo;
    private final TokenRepository tokenRepo;

    public QueueServiceImpl(QueuePositionRepository repo, TokenRepository tokenRepo) {
        this.repo = repo;
        this.tokenRepo = tokenRepo;
    }

    public QueuePosition updateQueuePosition(Long tokenId, int position) {
        if (position < 1) {
            throw new IllegalArgumentException(">= 1");
        }

        Token token = tokenRepo.findById(tokenId).orElseThrow();

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(position);

        return repo.save(qp);
    }

    public QueuePosition getPosition(Long tokenId) {
        return repo.findByToken_Id(tokenId).orElseThrow();
    }
}
