package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.impl.TokenLogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class TokenLogController {

    private final TokenLogServiceImpl service;

    public TokenLogController(TokenLogServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/{tokenId}")
    public ResponseEntity<TokenLog> addLog(
            @PathVariable Long tokenId,
            @RequestParam String message) {
        return ResponseEntity.ok(service.addLog(tokenId, message));
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<List<TokenLog>> getLogs(
            @PathVariable Long tokenId) {
        return ResponseEntity.ok(service.getLogs(tokenId));
    }
}
