package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceCounterServiceImpl {

    private final ServiceCounterRepository repo;

    public ServiceCounterServiceImpl(ServiceCounterRepository repo) {
        this.repo = repo;
    }

    public ServiceCounter save(ServiceCounter counter) {
        return repo.save(counter);                              // t21
    }
}
