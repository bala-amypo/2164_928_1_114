package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QueuePositionService {

    private final QueuePositionRepository repository;

    public QueuePositionService(QueuePositionRepository repository) {
        this.repository = repository;
    }

    public QueuePosition updateQueuePosition(Long tokenId, Integer newPosition) {
        if (newPosition < 1) {
            throw new RuntimeException("Invalid position");
        }

        QueuePosition qp = repository.findByToken_Id(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));

        qp.setPosition(newPosition);
        qp.setUpdatedAt(LocalDateTime.now());
        return repository.save(qp);
    }

    public QueuePosition getPosition(Long tokenId) {
        return repository.findByToken_Id(tokenId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
