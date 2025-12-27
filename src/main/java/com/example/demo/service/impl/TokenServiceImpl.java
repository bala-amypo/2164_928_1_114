package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

@Service   // 🔴 VERY IMPORTANT
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createToken(Long userId) {
        Token token = new Token();
        token.setUserId(userId);
        token.setStatus("ACTIVE");
        return tokenRepository.save(token);
    }

    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId).orElse(null);
    }
}
