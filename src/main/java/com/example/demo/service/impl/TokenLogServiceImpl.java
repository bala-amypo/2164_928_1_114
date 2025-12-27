package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository logRepository;

    public TokenLogServiceImpl(TokenLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public TokenLog log(Token token, String message) {
        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setMessage(message);
        log.setLoggedAt(LocalDateTime.now());
        return logRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogsByToken(Long tokenId) {
        return logRepository.findByToken_Id(tokenId);
    }
}
