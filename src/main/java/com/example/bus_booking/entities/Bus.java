package com.example.bus_booking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TransportCompany transportCompany;

    @NotNull
    @Length(max = 15)
    private String model;

    @PositiveOrZero
    private int availableSeats;

    private int yearOfManufacture;


    private LocalDateTime lastTODate;

    @Lob
    private byte[] busPhotos;
    @Lob
    private byte[] driverPhoto;
    @Lob
    private byte[] driverLicense;
    @Positive
    private int seatCount;

    private LocalDateTime availableStart;

    private LocalDateTime availableEnd;


    @Column(name = "avg_rating", columnDefinition = "NUMERIC(2, 1)")
    private Double avgRating;

    @PositiveOrZero
    private int minRentHours;
    @PositiveOrZero
    private int minCancelDay;
    @Positive
    private int priceWeekends;
    @Positive
    private int priceWeekdays;
    @Column(unique = true)
    private String registrationNumber;
    private boolean isActive;

    private boolean hasTv;
    private boolean hasWifi;
    private boolean hasAirConditioning;
    private boolean hasInteriorLighting;
    private boolean hasMicrophone;
    private boolean hasUsbCharger;
    private boolean hasUsbSync;
    private boolean hasAccessibilityFeatures;
}
