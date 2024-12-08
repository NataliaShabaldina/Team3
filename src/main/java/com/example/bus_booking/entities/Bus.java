package com.example.bus_booking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private TransportCompanies transportCompanies;
    private String model;
    private int yearOfManufacture;
    private LocalDate lastTODate;
    private byte[] busPhotos;
    private byte[] driverPhoto;
    private byte[] driverLicense;
    private int seatCount;
    private LocalDate availableStart;
    private LocalDate availableEnd;

    private double avgRating;

    private int minRentHours;
    private int minCancelDay;
    private int priceWeekends;
    private int priceWeekdays;

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
