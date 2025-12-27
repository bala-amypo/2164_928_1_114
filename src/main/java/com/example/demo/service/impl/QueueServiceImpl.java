package com.example.demo.service.impl;

import com.example.demo.repository.QueuePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl {

    private final QueuePositionRepository queuePositionRepository;
}
