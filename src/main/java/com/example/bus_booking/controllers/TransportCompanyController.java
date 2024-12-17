package com.example.bus_booking.controllers;


import com.example.bus_booking.entities.TransportCompany;
import com.example.bus_booking.services.TransportCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transport-companies")
@RequiredArgsConstructor

public class TransportCompanyController {
    private final TransportCompanyService transportCompanyService;

    @PostMapping
    public ResponseEntity<TransportCompany> createTransportCompany(@RequestBody TransportCompany transportCompany) {
        return ResponseEntity.ok(transportCompanyService.createTransportCompany(transportCompany));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportCompany> getTransportCompany(@PathVariable Long id) {
        return ResponseEntity.ok(transportCompanyService.getTransportCompanyById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransportCompany>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(transportCompanyService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportCompany> updateTransportCompany(@PathVariable Long id, @RequestBody TransportCompany updatedtransportCompany) {
        return ResponseEntity.ok(transportCompanyService.updateTransportCompany(id, updatedtransportCompany));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportCompany(@PathVariable Long id) {
        transportCompanyService.deleteTransportCompany(id);
        return ResponseEntity.noContent().build();
    }
}
