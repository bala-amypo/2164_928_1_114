package com.example.demo.service;

import com.example.demo.entity.TokenLog;
import java.util.List;

public interface TokenLogService {

    void addLog(Long tokenId, String message);

    List<TokenLog> getLogs(Long tokenId);
}
