package com.example.bus_booking.services;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.RegisterRequest;
import com.example.bus_booking.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client createClient(Client client) {
        client.setId(null);
        return clientRepository.saveAndFlush(client);
    }

    public Client findByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    public Client updateProfile(String firstName,String lastName, Client updatedClient) {
        Client client = findByFirstNameAndLastName(firstName, lastName);
        client.setEmail(updatedClient.getEmail());
        client.setPhoneNumber(updatedClient.getPhoneNumber());
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

    public Client getClientByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(clientRepository.findByPhoneNumber(phoneNumber))
                .orElseThrow(() -> new NoSuchElementException("Клиент с номером телефона " + phoneNumber + " не найден"));
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
        if (client != null) {
            clientRepository.delete(client);
        } else {
            throw new NoSuchElementException("Клиент с ID " + clientId + " не найден");
        }
    }


    public boolean isVerified(Long clientId) {
        return clientRepository.existsByIdAndIsVerifiedMailTrue(clientId);
    }

    public boolean isEmailVerified(Long clientId) {
        Client client = getClientById(clientId);
        return client.isVerifiedMail();
    }

    public ResponseEntity<?> registerClient(RegisterRequest registerRequest) {
        if (clientRepository.findByEmail(registerRequest.getEmail()) !=null) {
            return new ResponseEntity<>("Почта уже используется", HttpStatus.BAD_REQUEST);
        }

        Client client = new Client();
        client.setFirstName(registerRequest.getName());
        client.setLastName(registerRequest.getLastName());
        client.setEmail(registerRequest.getEmail());
        client.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        clientRepository.save(client);
        return new ResponseEntity<>("Регистрация успешна", HttpStatus.OK);
    }

    public boolean isClientExist(String email) {
        return clientRepository.existsByEmail(email);
    }

}
