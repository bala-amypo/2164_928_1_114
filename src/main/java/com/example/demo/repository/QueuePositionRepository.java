package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.QueuePosition;

public interface QueuePositionRepository extends JpaRepository<QueuePosition, Long> {
}
