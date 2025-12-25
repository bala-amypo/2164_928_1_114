package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TokenLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Token token;

    private String logMessage;
    private LocalDateTime loggedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Token getToken() { return token; }
    public String getMessage() { return logMessage; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    public void setId(Long id) { this.id = id; }
    public void setToken(Token token) { this.token = token; }
    public void setLogMessage(String logMessage) { this.logMessage = logMessage; }
}
