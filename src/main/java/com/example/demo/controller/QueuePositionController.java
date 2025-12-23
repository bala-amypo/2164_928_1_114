package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    private final QueuePositionService service;

    public QueuePositionController(QueuePositionService service) {
        this.service = service;
    }

    @PutMapping("/position/{tokenId}/{newPosition}")
    public QueuePosition update(@PathVariable Long tokenId,
                                @PathVariable Integer newPosition) {
        return service.updateQueuePosition(tokenId, newPosition);
    }

    @GetMapping("/position/{tokenId}")
    public QueuePosition get(@PathVariable Long tokenId) {
        return service.getPosition(tokenId);
    }
}
