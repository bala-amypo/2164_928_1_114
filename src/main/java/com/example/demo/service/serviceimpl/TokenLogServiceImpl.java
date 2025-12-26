package com.example.demo.service.impl;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenLogServiceImpl {

    private final TokenLogRepository repo;

    public TokenLogServiceImpl(TokenLogRepository repo) {
        this.repo = repo;
    }

    public TokenLog save(TokenLog log) {
        return repo.save(log);                                  // t24
    }
}
