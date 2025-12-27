package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class TokenLogController {

    private final TokenLogService logService;

    public TokenLogController(TokenLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{tokenId}")
    public TokenLog addLog(@PathVariable Long tokenId,
                            @RequestParam String message) {
        return logService.addLog(tokenId, message);
    }
}
