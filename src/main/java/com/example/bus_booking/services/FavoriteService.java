package com.example.bus_booking.services;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.Favorites;
import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.repositories.BusRepository;
import com.example.bus_booking.repositories.ClientRepository;
import com.example.bus_booking.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ClientRepository clientRepository;
    private final BusRepository busRepository;
    private final ClientService clientService;
    private final BusService busService;

    public List<Bus> getFavoriteBusesByClient(Long clientId) {
        List<Favorites> favorites = favoriteRepository.findAllByClientId(clientId);
        List<Bus> buses = new ArrayList<>();
        for (Favorites favorite : favorites) {
            buses.add(favorite.getBus());
        }
        return buses;
    }

    public void addToFavorites(String firstName,String lastName, Long busId) {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (client == null) {
            throw new IllegalStateException("Клиент не найден");
        }

        Favorites existingFavorite = favoriteRepository.findByClientIdAndBusId(client.getId(), busId);
        if (existingFavorite != null) {
            throw new IllegalStateException("Этот автобус уже в избранных");
        }

        Bus bus = busRepository.findById(busId).orElseThrow(() -> new IllegalStateException("Автобус не найден"));
        Favorites favorite = new Favorites();
        favorite.setClientId(client.getId());
        favorite.setBus(bus);
        favoriteRepository.save(favorite);
    }

    public void removeFavorite(String firstName, String lastName, Long busId) {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName); // Пример
        if (client == null) {
            throw new IllegalStateException("Client not found");
        }

        Favorites existingFavorite = favoriteRepository.findByClientIdAndBusId(client.getId(), busId);
        if (existingFavorite == null) {
            throw new IllegalStateException("This bus is not in your favorites");
        }

        favoriteRepository.delete(existingFavorite);
    }
}
