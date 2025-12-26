package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl {

    private final QueuePositionRepository repository;

    public QueueServiceImpl(QueuePositionRepository repository) {
        this.repository = repository;
    }

    public QueuePosition updateQueuePosition(Long id, Integer pos) {
        QueuePosition qp = repository.findById(id)
                .orElse(new QueuePosition());
        qp.setPosition(pos);
        return repository.save(qp);
    }

    public Integer getPosition(Long id) {
        return repository.findById(id)
                .map(QueuePosition::getPosition)
                .orElse(0);
    }
}
