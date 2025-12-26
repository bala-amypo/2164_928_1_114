package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.impl.QueueServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueServiceImpl service;

    public QueueController(QueueServiceImpl service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public QueuePosition update(@PathVariable Long id,
                                @RequestParam Integer position) {
        return service.updateQueuePosition(id, position);
    }

    @GetMapping("/{id}")
    public QueuePosition get(@PathVariable Long id) {
        return service.updateQueuePosition(id, 0); // or fetch entity
    }
}
