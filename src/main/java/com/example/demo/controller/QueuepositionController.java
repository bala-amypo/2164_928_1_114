package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueuePositionController {

    @GetMapping("/queue")
    public String queue() {
        return "Queue API Working";
    }
}
