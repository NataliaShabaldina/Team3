package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public Client createClient(@RequestBody Client client) {
        return  ResponseEntity.ok(clientService.createClient(client));
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        return ResponseEntity.ok(client);
    }


    @GetMapping("/email")
    public Client getClientByEmail(@RequestParam String email) {
        return clientService.getClientByEmail(email);
    }

    @PutMapping("/update/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @RequestBody Client updateClient) {
        return clientService.updateClient(clientId, updateClient);
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

    @GetMapping("/isVerified")
    public Boolean isEmailVerified(@RequestParam Long clientId) {
        return clientService.isEmailVerified(clientId);
    }
}
