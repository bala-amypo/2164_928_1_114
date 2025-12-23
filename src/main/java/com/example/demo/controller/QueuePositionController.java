package com.example.demo.controller;

/*
 * QueuePosition Controller
 * Handles queue position related operations
 */

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    private final QueueService queueService;

    // Constructor Injection
    public QueuePositionController(QueueService queueService) {
        this.queueService = queueService;
    }

    /*
     * Get queue position details using token ID
     */
    @GetMapping("/position/{tokenId}")
    public QueuePosition getQueuePosition(@PathVariable Long tokenId) {
        return queueService.getQueuePosition(tokenId);
    }
}
