package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   // ✅ THIS FIXES builder() ERROR
public class TokenLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logMessage;

    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;
}
