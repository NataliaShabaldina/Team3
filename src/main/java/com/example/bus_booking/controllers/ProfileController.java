package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.Orders;
import com.example.bus_booking.services.ClientService;
import com.example.bus_booking.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ClientService clientService;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Client> getProfile(Principal principal) {
        String clientName = principal.getName();
        String[] clientNameParts = clientName.split(" ");
        String firstName = clientNameParts[0];
        String lastName = clientNameParts[1];
        Client client = clientService.findByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(client);
    }

//    @GetMapping("/bookings")
//    public ResponseEntity<List<Orders>> getBookings(Principal principal) {
//        String clientName = principal.getName();
//        String[] clientNameParts = clientName.split(" ");
//        String firstName = clientNameParts[0];
//        String lastName = clientNameParts[1];
//
//        List<Orders> orders = orderService.getO;
//        return ResponseEntity.ok(orders);
//    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Orders>> getBookings(Principal principal) {
        String clientName = principal.getName();
        String[] clientNameParts = clientName.split(" ");
        String firstName = clientNameParts[0];
        String lastName = clientNameParts[1];

        Client client = clientService.findByFirstNameAndLastName(firstName, lastName);
        Long clientId = client.getId();
        List<Orders> orders = orderService.getOrdersByClientId(clientId);
        return ResponseEntity.ok(orders);
    }


    @PutMapping
    public ResponseEntity<Client> updateProfile(@RequestBody Client updatedClient, Principal principal) {
        String clientName = principal.getName();
        String[] clientNameParts = clientName.split(" ");
        String firstName = clientNameParts[0];
        String lastName = clientNameParts[1];

        // Обновляем профиль клиента
        Client client = clientService.updateProfile(firstName, lastName, updatedClient);
        return ResponseEntity.ok(client);
    }
}
