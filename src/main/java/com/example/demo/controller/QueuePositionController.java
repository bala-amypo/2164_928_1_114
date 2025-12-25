package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.impl.QueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    @GetMapping("/position")
    public String getPosition() {
        return "position";
    }

} // <- closing brace
