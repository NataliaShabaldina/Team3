package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;

    @PostMapping("/create")
    public Bus createBus(@RequestBody Bus bus) {
        return busService.saveBus(bus);
    }

    @GetMapping("/{busId}")
    public Bus getBusById(@PathVariable Long busId) {
        return busService.findBusById(busId).orElseThrow(() -> new RuntimeException("Автобус не найден"));
    }

    @GetMapping("/model")
    public List<Bus> getBusesByModel(@RequestParam String model) {
        return busService.findBusesByModel(model);
    }

    @GetMapping("/search")
    public List<Bus> searchBuses(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean airConditioning,
            @RequestParam(required = false) Integer minSeats) {
        LocalDateTime start = LocalDateTime.from(LocalDate.parse(startDate));
        LocalDateTime end = LocalDateTime.from(LocalDate.parse(endDate));
        return busService.searchAvailableBuses(start, end, wifi, airConditioning, minSeats);
    }

    @GetMapping("/{busId}")
    public Bus getBusDetails(@PathVariable Long busId) {
        return busService.getBusDetails(busId);
    }


    @PutMapping("/update/{busId}")
    public Bus updateBus(@PathVariable Long busId, @RequestBody Bus busDetails) {
        return busService.updateBus(busId, busDetails);
    }

    @DeleteMapping("/delete/{busId}")
    public void deleteBus(@PathVariable Long busId) {
        Bus bus = busService.findBusById(busId).orElseThrow(() -> new RuntimeException("Автобус не найден"));
        busService.deleteBus(bus);
    }

    @GetMapping("/all")
    public List<Bus> getAllBuses() {
        return busService.findAllBuses();
    }

    @GetMapping("/available")
    public List<Bus> getAvailableBuses(@RequestParam String startTime, @RequestParam String endTime) {
        LocalDateTime startDate = LocalDateTime.parse(startTime);
        LocalDateTime endDate = LocalDateTime.parse(endTime);
        return busService.findAvailableBuses(startDate, endDate);
    }
}

