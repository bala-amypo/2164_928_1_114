package com.example.demo.service;

import com.example.demo.entity.QueuePosition;

public interface QueuePositionService {

    QueuePosition updateQueuePosition(Long tokenId, Integer newPosition);

    QueuePosition getPosition(Long tokenId);
}
