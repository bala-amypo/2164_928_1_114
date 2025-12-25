package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.impl.ServiceCounterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/counters")
public class ServiceCounterController {

    private final ServiceCounterServiceImpl service;

    public ServiceCounterController(ServiceCounterServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceCounter> addCounter(
            @RequestBody ServiceCounter counter) {
        return ResponseEntity.ok(service.addCounter(counter));
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceCounter>> getActiveCounters() {
        return ResponseEntity.ok(service.getActiveCounters());
    }
}
