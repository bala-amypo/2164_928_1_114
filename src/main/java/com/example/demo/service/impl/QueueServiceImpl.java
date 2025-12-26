package com.example.demo.service.impl;

import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    @Override
    public void updateQueuePosition(Long queueId, Integer position) {
        // TODO: implement logic later
        System.out.println("Queue " + queueId + " updated to position " + position);
    }
}
