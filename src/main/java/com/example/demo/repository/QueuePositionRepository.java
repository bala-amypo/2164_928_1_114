package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface QueuePositionRepository extends JpaRepository<QueuePosition, Long> {
    Optional<QueuePosition> findByToken_Id(Long tokenId);
}