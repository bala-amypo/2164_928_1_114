package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/token-logs")
public class TokenLogController {

    @Autowired
    private TokenLogRepository tokenLogRepository;

    @Autowired
    private TokenRepository tokenRepository;

    // Get all token logs
    @GetMapping
    public List<TokenLog> getAllLogs() {
        return tokenLogRepository.findAll();
    }

    // Log token action (ISSUED, CALLED, COMPLETED)
    @PostMapping("/log/{tokenId}")
    public TokenLog logTokenAction(@PathVariable Long tokenId,
                                   @RequestParam String action,
                                   @RequestParam(required = false) String remarks) {

        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setAction(action);
        log.setRemarks(remarks);
        log.setTimestamp(LocalDateTime.now());

        return tokenLogRepository.save(log);
    }
}
