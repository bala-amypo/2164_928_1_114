package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service   // THIS is what creates the bean
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepo;

    public QueueServiceImpl(QueuePositionRepository queueRepo) {
        this.queueRepo = queueRepo;
    }

    @Override
    public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {
        QueuePosition position = queueRepo.findByTokenId(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        position.setPosition(newPosition);
        return queueRepo.save(position);
    }

    @Override
    public QueuePosition getPosition(Long tokenId) {
        return queueRepo.findByTokenId(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
