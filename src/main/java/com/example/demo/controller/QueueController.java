package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/{tokenId}")
    public QueuePosition add(@PathVariable Long tokenId) {
        return queueService.addToQueue(tokenId);
    }

    @GetMapping("/{tokenId}")
    public QueuePosition get(@PathVariable Long tokenId) {
        return queueService.getQueuePosition(tokenId);
    }

    @GetMapping
    public List<QueuePosition> all() {
        return queueService.getAll();
    }
}
