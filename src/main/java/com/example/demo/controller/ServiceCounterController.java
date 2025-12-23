package com.example.demo.controller;

/*
 * Service Counter Controller
 * Handles counter-related operations
 */

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;

@RestController
@RequestMapping("/counter")
public class ServiceCounterController {

    private final ServiceCounterService counterService;

    public ServiceCounterController(ServiceCounterService counterService) {
        this.counterService = counterService;
    }

    /*
     * Get all active service counters
     */
    @GetMapping("/active")
    public List<ServiceCounter> getActiveCounters() {
        return counterService.getActiveCounters();
    }
}
