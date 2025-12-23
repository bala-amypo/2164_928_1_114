package com.example.demo.controller;

/*
 * Token Log Controller
 * Handles token log related operations
 */

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;

@RestController
@RequestMapping("/token-logs")
public class TokenLogController {

    private final TokenLogService tokenLogService;

    // Constructor Injection
    public TokenLogController(TokenLogService tokenLogService) {
        this.tokenLogService = tokenLogService;
    }

    /*
     * Get all logs for a given token ID
     */
    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getLogsByTokenId(@PathVariable Long tokenId) {
        return tokenLogService.getLogsByTokenId(tokenId);
    }
}
