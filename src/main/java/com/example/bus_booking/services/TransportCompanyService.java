package com.example.bus_booking.services;

import com.example.bus_booking.entities.TransportCompany;
import com.example.bus_booking.repositories.TransportCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TransportCompanyService {
    private final TransportCompanyRepository transportCompanyRepository;

    public TransportCompany createTransportCompany(TransportCompany transportCompany) {
        return transportCompanyRepository.save(transportCompany);
    }

    public TransportCompany getTransportCompanyById(Long id) {
        return transportCompanyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Транспортная компания с ID " + id + " не найдена"));
    }


    public List<TransportCompany> findByName(String name) {
        return transportCompanyRepository.findByName(name);
    }

    public TransportCompany updateTransportCompany(Long id, TransportCompany updatedTransportCompany) {
        TransportCompany existingCompany = getTransportCompanyById(id);
        existingCompany.setName(updatedTransportCompany.getName());
        existingCompany.setEmail(updatedTransportCompany.getEmail());
        existingCompany.setPhone(updatedTransportCompany.getPhone());
        existingCompany.setLicense(updatedTransportCompany.getLicense());
        existingCompany.setProfilePhoto(updatedTransportCompany.getProfilePhoto());
        return transportCompanyRepository.save(existingCompany);
    }


    public void deleteTransportCompany(Long id) {
        transportCompanyRepository.deleteById(id);
    }
}
