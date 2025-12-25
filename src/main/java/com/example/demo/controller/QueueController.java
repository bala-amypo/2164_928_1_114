package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.impl.QueueServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private final QueueServiceImpl service;

    public QueueController(QueueServiceImpl service) {
        this.service = service;
    }

    @PutMapping("/{tokenId}")
    public ResponseEntity<QueuePosition> updatePosition(
            @PathVariable Long tokenId,
            @RequestParam Integer position) {
        return ResponseEntity.ok(
                service.updateQueuePosition(tokenId, position)
        );
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<QueuePosition> getPosition(
            @PathVariable Long tokenId) {
        return ResponseEntity.ok(service.getPosition(tokenId));
    }
}
