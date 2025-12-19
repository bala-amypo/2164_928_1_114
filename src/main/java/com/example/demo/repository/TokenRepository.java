package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TokenLog;

public interface TokenLogRepository extends JpaRepository<TokenLog, Long> {
}
