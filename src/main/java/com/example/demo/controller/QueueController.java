package com.example.demo.controller;

import com.example.demo.service.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    // Add message to queue
    @PostMapping("/enqueue")
    public ResponseEntity<String> enqueue(@RequestParam String message) {
        queueService.enqueue(message);
        return ResponseEntity.ok("Message added to queue");
    }

    // Remove message from queue
    @GetMapping("/dequeue")
    public ResponseEntity<String> dequeue() {
        String message = queueService.dequeue();
        if (message == null) {
            return ResponseEntity.ok("Queue is empty");
        }
        return ResponseEntity.ok(message);
    }

    // Peek queue
    @GetMapping("/peek")
    public ResponseEntity<String> peek() {
        String message = queueService.peek();
        if (message == null) {
            return ResponseEntity.ok("Queue is empty");
        }
        return ResponseEntity.ok(message);
    }

    // Queue size
    @GetMapping("/size")
    public ResponseEntity<Integer> size() {
        return ResponseEntity.ok(queueService.size());
    }
}
