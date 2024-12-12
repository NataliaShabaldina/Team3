package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
    Favorites findByClientIdAndBusId(Long clientId, Long busId);

    List<Favorites> findAllByClientId(Long clientId);
}
