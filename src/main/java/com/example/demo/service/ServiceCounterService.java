package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;

@Service
public class ServiceCounterService {

    @Autowired
    private ServiceCounterRepository serviceCounterRepository;

    public List<ServiceCounter> getAllServiceCounters() {
        return serviceCounterRepository.findAll();
    }
}
