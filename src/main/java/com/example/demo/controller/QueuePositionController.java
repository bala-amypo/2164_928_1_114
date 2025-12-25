package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.impl.QueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    @Autowired
    private QueueServiceImpl queueService;

    // Update the position of a token in the queue
    @PutMapping("/update/{tokenId}")
    public QueuePosition updateQueuePosition(
