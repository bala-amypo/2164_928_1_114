package com.example.demo.entity;

/*
 * Service Counter Entity
 */

import jakarta.persistence.*;

@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;
    private String department;
    private boolean isActive;

    public ServiceCounter() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
}
