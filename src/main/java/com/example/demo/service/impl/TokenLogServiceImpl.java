package com.example.demo.service.impl;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service   // 🔴 REQUIRED
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository logRepository;

    public TokenLogServiceImpl(TokenLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public TokenLog addLog(Long tokenId, String message) {
        TokenLog log = new TokenLog();
        log.setTokenId(tokenId);
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        return logRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogs(Long tokenId) {
        return logRepository.findByTokenId(tokenId);
    }
}
