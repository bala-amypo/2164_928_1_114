package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counters")
public class ServiceCounterController {

    private final ServiceCounterService service;

    public ServiceCounterController(ServiceCounterService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ServiceCounter add(@RequestBody ServiceCounter counter) {
        return service.addCounter(counter);
    }

    @GetMapping("/active")
    public List<ServiceCounter> active() {
        return service.getActiveCounters();
    }
}
