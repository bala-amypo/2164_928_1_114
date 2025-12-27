package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "queue_positions")
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "position")
    private Integer position;

    // -------- Getters --------

    public Long getId() {
        return id;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public Integer getPosition() {
        return position;
    }

    // -------- Setters --------

    public void setId(Long id) {
        this.id = id;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
