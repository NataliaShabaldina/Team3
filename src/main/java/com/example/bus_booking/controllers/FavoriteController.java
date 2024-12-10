package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.entities.Favorites;
import com.example.bus_booking.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public Favorites addFavorite(@RequestParam Long clientId, @RequestParam Long busId) {
        return favoriteService.addFavorite(clientId, busId);
    }

    @DeleteMapping("/remove")
    public void removeFavorite(@RequestParam Long clientId, @RequestParam Long busId) {
        favoriteService.removeFavorite(clientId, busId);
    }

    @GetMapping("/client/{clientId}")
    public List<Bus> getFavoriteBusesByClient(@PathVariable Long clientId) {
        return favoriteService.getFavoriteBusesByClient(clientId);
    }
}
