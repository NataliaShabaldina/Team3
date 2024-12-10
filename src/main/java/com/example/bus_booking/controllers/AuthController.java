package com.example.bus_booking.controllers;

import com.example.bus_booking.services.ConfirmationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ConfirmationCodeService confirmationCodeService;

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam Long clientId, @RequestParam String code) {
        if (confirmationCodeService.validateCode(code, clientId)) {
            return ResponseEntity.ok("Код подтверждён");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный код подтверждения");
    }
}
