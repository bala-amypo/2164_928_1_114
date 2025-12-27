package com.example.demo.service.impl;

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

    private final TokenLogRepository tokenLogRepository;

    @Override
    public TokenLog addLog(Long tokenId, String message) {
        TokenLog log = TokenLog.builder()
                .tokenId(tokenId)
                .logMessage(message)
                .loggedAt(LocalDateTime.now())
                .build();
        return tokenLogRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogs(Long tokenId) {
        return tokenLogRepository.findByTokenIdOrderByLoggedAtAsc(tokenId);
    }
}
