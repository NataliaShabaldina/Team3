package com.example.bus_booking.services;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.Favorites;
import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.repositories.BusRepository;
import com.example.bus_booking.repositories.ClientRepository;
import com.example.bus_booking.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ClientRepository clientRepository;
    private final BusRepository busRepository;

    @Transactional
    public Favorites addFavorite(Long clientId, Long busId) {
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Bus> bus = busRepository.findById(busId);

        if (client.isPresent() && bus.isPresent()) {
            Favorites favorite = new Favorites();
            favorite.setClient(client.get());
            favorite.setBus(bus.get());
            return favoriteRepository.save(favorite);
        } else {
            throw new RuntimeException("Клиент или автобус не найдены");
        }
    }

    @Transactional
    public void removeFavorite(Long clientId, Long busId) {
        favoriteRepository.deleteByClientIdAndBusId(clientId, busId);
    }

    public List<Bus> getFavoriteBusesByClient(Long clientId) {
        return favoriteRepository.findFavoriteBusesByClientId(clientId);
    }
}
