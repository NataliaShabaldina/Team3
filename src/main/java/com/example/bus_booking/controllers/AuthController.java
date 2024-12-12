package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.RegisterRequest;
import com.example.bus_booking.services.ClientService;
import com.example.bus_booking.services.ConfirmationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ConfirmationCodeService confirmationCodeService;
    private final ClientService clientService;

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam Long clientId, @RequestParam String code) {
        if (confirmationCodeService.validateCode(code, clientId)) {
            return ResponseEntity.ok("Код подтверждён");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный код подтверждения");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody RegisterRequest registerRequest) {
        try {
            if (clientService.isClientExist(registerRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Клиент с такой почтой уже существует!");
            }
            clientService.registerClient(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Вы зарегистрированы");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
