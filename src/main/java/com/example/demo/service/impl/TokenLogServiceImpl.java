package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository logRepository;

    @Override
    public void addLog(Long tokenId, String message) {
        TokenLog log = new TokenLog();
        log.setLoggedAt(LocalDateTime.now());
        log.setLogMessage(message);

        Token token = new Token();
        token.setId(tokenId);
        log.setToken(token);

        logRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogs(Long tokenId) {
        return logRepository.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
