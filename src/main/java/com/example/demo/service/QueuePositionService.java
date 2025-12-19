package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;

@Service
public class QueuePositionService {

    @Autowired
    private QueuePositionRepository queuePositionRepository;

    public List<QueuePosition> getAllQueuePositions() {
        return queuePositionRepository.findAll();
    }
}
