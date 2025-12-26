package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_counter")
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;
    private String department;

    @Column(nullable = false)
    private Boolean active = true;

    // 🔴 THIS METHOD FIXES YOUR ERROR
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getCounterName() {
        return counterName;
    }

    public String getDepartment() {
        return department;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
