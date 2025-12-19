package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceCounterController {

    @GetMapping("/service-counter")
    public String getServiceCounter() {
        return "Service Counter Controller Working";
    }
}
