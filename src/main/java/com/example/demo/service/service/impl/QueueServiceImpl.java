package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl {

    private final QueuePositionRepository repo;

    public QueueServiceImpl(QueuePositionRepository repo) {
        this.repo = repo;
    }

    public QueuePosition updatePosition(QueuePosition qp, int position) {
        if (position < 1) {                                     // t67
            throw new IllegalArgumentException("Invalid position");
        }
        qp.setPosition(position);
        return repo.save(qp);                                   // t23
    }
}
