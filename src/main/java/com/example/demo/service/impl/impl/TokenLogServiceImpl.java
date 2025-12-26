package com.example.demo.service.impl;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenLogServiceImpl {

    private final TokenLogRepository repository;

    public TokenLogServiceImpl(TokenLogRepository repository) {
        this.repository = repository;
    }

    public TokenLog addLog(Long tokenId, String msg) {
        TokenLog log = new TokenLog();
        log.setMessage(msg);
        return repository.save(log);
    }

    public List<TokenLog> getLogs(Long tokenId) {
        return repository.findAll();
    }
}
