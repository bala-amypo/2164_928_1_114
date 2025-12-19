package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;

@Service
public class TokenLogService {

    @Autowired
    private TokenLogRepository tokenLogRepository;

    public List<TokenLog> getAllTokenLogs() {
        return tokenLogRepository.findAll();
    }
}
