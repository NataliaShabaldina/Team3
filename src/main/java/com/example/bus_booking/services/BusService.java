package com.example.bus_booking.services;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.repositories.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    public List<Bus> findBusesByModel(String model) {
        return busRepository.findByModel(model);
    }

    public List<Bus> findAvailableBuses(LocalDateTime startDate, LocalDateTime endDate) {
        return busRepository.findAvailableBuses(startDate, endDate);
    }

    public Optional<Bus> findBusById(Long id) {
        return busRepository.findById(id);
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Bus bus) {
        busRepository.delete(bus);
    }
    public Bus updateBus(Long id, Bus busDetails) {
        Optional<Bus> existingBus = busRepository.findById(id);
        if (existingBus.isPresent()) {
            Bus bus = existingBus.get();
            bus.setModel(busDetails.getModel());
            bus.setYearOfManufacture(busDetails.getYearOfManufacture());
            bus.setLastTODate(busDetails.getLastTODate());
            bus.setBusPhotos(busDetails.getBusPhotos());
            bus.setDriverPhoto(busDetails.getDriverPhoto());
            bus.setDriverLicense(busDetails.getDriverLicense());
            bus.setSeatCount(busDetails.getSeatCount());
            bus.setHasTv(busDetails.isHasTv());
            bus.setHasWifi(busDetails.isHasWifi());
            bus.setHasAirConditioning(busDetails.isHasAirConditioning());
            bus.setHasInteriorLighting(busDetails.isHasInteriorLighting());
            bus.setHasMicrophone(busDetails.isHasMicrophone());
            bus.setHasUsbCharger(busDetails.isHasUsbCharger());
            bus.setHasUsbSync(busDetails.isHasUsbSync());
            bus.setHasAccessibilityFeatures(busDetails.isHasAccessibilityFeatures());
            bus.setAvailableStart(busDetails.getAvailableStart());
            bus.setAvailableEnd(busDetails.getAvailableEnd());
            return busRepository.save(bus);
        } else {
            return null; //выбросить исключение?
        }
    }

    public double getAverageRatingByBusId(Long busId) {
        return busRepository.findAvgRatingByBusId(busId) != null ? busRepository.findAvgRatingByBusId(busId) : 0.0;
    }

    public List<Bus> findAllBuses() {
        return busRepository.findAll();
    }

   public List<Bus> searchAvailableBuses(LocalDateTime start, LocalDateTime end, Boolean wifi, Boolean airConditioning, Integer minSeats) {
        return busRepository.findAvailableBusesWithFilters(start, end, wifi, airConditioning, minSeats);
   }

    public Bus getBusDetails(Long busId) {
        return busRepository.findById(busId).orElseThrow(() -> new RuntimeException("Bus not found"));
    }

}
