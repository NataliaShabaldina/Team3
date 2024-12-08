package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT f.bus FROM Favorites f WHERE f.client.id = :clientId")
    List<Bus> findFavoriteBusesByClientId(@Param("clientId") Long clientId);

}