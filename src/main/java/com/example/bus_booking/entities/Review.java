package com.example.bus_booking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bus bus;
    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private double rating;

    @Lob
    private String text;
    private LocalDateTime createdTime = LocalDateTime.now();

    @Lob
    private String answer;
}
