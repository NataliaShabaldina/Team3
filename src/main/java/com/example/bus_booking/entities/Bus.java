package com.example.bus_booking.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    int id;
    int companyId;
    String model;
    int yearOfManufacture;
    LocalDate lastTODate;
    byte[] busPhotos;
    byte[] driverPhoto;
    byte[] driverLicense;
    int seatCount;
    boolean hasTv;
    boolean hasWifi;
    boolean hasAirConditioning;
    boolean hasInteriorLighting;
    boolean hasMicrophone;
    boolean hasUsbCharger;
    boolean hasUsbSync;
    boolean hasAccessibilityFeatures;
}
