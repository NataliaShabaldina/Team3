package com.example.bus_booking.controllers;

import com.example.bus_booking.services.YookassaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final YookassaService yookassaService;


    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody BigDecimal amount, @RequestParam String returnUrl) {
        String paymentUrl = yookassaService.createPayment(amount, returnUrl);
        return ResponseEntity.ok(paymentUrl);
    }
}
