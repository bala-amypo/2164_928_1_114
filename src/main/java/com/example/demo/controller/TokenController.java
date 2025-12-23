package com.example.demo.controller;

/*
 * Token Controller
 * Handles token issue and status updates
 */

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    // Constructor Injection
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /*
     * Issue a new token for a service counter
     */
    @PostMapping("/issue/{counterId}")
    public Token issueToken(@PathVariable Long counterId) {
        return tokenService.issueToken(counterId);
    }

    /*
     * Update token status (WAITING / SERVING / COMPLETED)
     */
    @PutMapping("/status/{tokenId}")
    public Token updateStatus(@PathVariable Long tokenId,
                              @RequestParam String status) {
        return tokenService.updateStatus(tokenId, status);
    }
}
