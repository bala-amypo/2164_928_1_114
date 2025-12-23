package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(Long counterId, String status);
    Optional<Token> findByTokenNumber(String tokenNumber);
}