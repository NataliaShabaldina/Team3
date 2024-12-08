package com.example.bus_booking.services;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Клиент с id " + clientId + " не найден"));
    }

    public Client getClientByEmail(String email) {
        return Optional.ofNullable(clientRepository.findByEmail(email))
                .orElseThrow(() -> new NoSuchElementException("Клиент с email" + email + " не найден"));
    }

    public Client getClientByPhoneNumber(String phoneNuber) {
        return Optional.ofNullable(clientRepository.findByPhoneNumber(phoneNuber))
                .orElseThrow(() -> new NoSuchElementException("Клиент с номером телефона " + phoneNuber + " не найден"));
    }

    public Client updateClient(Long clientId, Client updatedClient) {
        Client client = getClientById(clientId);
        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setMiddleName(updatedClient.getMiddleName());
        client.setProfilePhotos(updatedClient.getProfilePhotos());
        client.setEmail(updatedClient.getEmail());
        client.setPhoneNumber(updatedClient.getPhoneNumber());
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        Client client = getClientById(clientId);
        clientRepository.delete(client);
    }

    public boolean isVerified(Long clientId) {
        return clientRepository.existsByIdAndIsVerifiedTrue(clientId);
    }
}
