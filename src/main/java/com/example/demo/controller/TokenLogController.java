package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class TokenLogController {

    private final TokenLogService service;

    public TokenLogController(TokenLogService service) {
        this.service = service;
    }

    @PostMapping("/{tokenId}")
    public TokenLog add(@PathVariable Long tokenId,
                        @RequestParam String message) {
        return service.addLog(tokenId, message);
    }

    @GetMapping("/{tokenId}")
    public List<TokenLog> getLogs(@PathVariable Long tokenId) {
        return service.getLogs(tokenId);
    }
}
