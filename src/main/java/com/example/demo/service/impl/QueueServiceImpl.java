package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ THIS FIXES YOUR ERROR
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepository;

    public QueueServiceImpl(QueuePositionRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public QueuePosition addToQueue(Long tokenId) {
        QueuePosition qp = new QueuePosition();
        qp.setTokenId(tokenId);
        return queueRepository.save(qp);
    }

    @Override
    public QueuePosition getQueuePosition(Long tokenId) {
        return queueRepository.findByTokenId(tokenId);
    }

    @Override
    public List<QueuePosition> getAll() {
        return queueRepository.findAll();
    }
}
