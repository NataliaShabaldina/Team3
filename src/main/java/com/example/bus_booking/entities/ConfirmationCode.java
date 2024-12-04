package com.example.bus_booking.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ConfirmationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;
    private String code;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime expiredTime;
    private Boolean isUsed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
