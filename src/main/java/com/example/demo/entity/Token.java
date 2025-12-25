package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber;
    private String status;

    private LocalDateTime issuedAt;
    private LocalDateTime completedAt;

    @ManyToOne
    private ServiceCounter serviceCounter;

    public Long getId() { return id; }
    public String getTokenNumber() { return tokenNumber; }
    public String getStatus() { return status; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public ServiceCounter getServiceCounter() { return serviceCounter; }

    public void setId(Long id) { this.id = id; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    public void setStatus(String status) { this.status = status; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
}
