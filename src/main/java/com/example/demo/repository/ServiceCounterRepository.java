package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ServiceCounter;

public interface ServiceCounterRepository extends JpaRepository<ServiceCounter, Long> {
}
