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
    public TokenLog log(Token token, String message) {
        TokenLog log = TokenLog.builder()
                .token(token)
                .logMessage(message)
                .loggedAt(LocalDateTime.now())
                .build();

        return logRepository.save(log);
    }

    @Override
    public List<TokenLog> getLogsByToken(Long tokenId) {
        return logRepository.findByToken_Id(tokenId);
    }
}
