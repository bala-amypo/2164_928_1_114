package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("isActive")
    private Boolean active = true;

    public ServiceCounter() {}

    public Long getId() {
        return id;
    }

    public String getCounterName() {
        return counterName;
    }

    public String getDepartment() {
        return department;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
