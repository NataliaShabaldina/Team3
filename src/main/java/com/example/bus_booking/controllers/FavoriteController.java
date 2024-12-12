package com.example.bus_booking.controllers;


import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.entities.FavoriteRequest;
import com.example.bus_booking.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

//    @PostMapping("/add")
//    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest favoriteRequest, Principal principal) {
//        try {
//            String username = principal.getName();
//
//            favoriteService.addToFavorites(username, favoriteRequest.getBusId());
//            return ResponseEntity.status(HttpStatus.CREATED).body("Добавлено в избранное");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не получилось добавить в избранное");
//        }
//    }

    @PostMapping("/{busId}")
    public ResponseEntity<Void> addToFavorites(@PathVariable Long busId, Principal principal) {
        String[] nameParts = principal.getName().split(" "); // Разделяем имя и фамилию (если имя и фамилия через пробел)
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        favoriteService.addToFavorites(firstName, lastName, busId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Long busId) {
        try {
            favoriteService.removeFavorite(firstName, lastName, busId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Вы удалили из избранных");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не получилось удалить");
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Bus>> getFavoriteBusesByClient(@PathVariable Long clientId) {
        List<Bus> favoriteBuses = favoriteService.getFavoriteBusesByClient(clientId);
        return ResponseEntity.ok(favoriteBuses);
    }


    @DeleteMapping("/{busId}")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Long busId, Principal principal) {
        try {
            String username = principal.getName();

            String[] nameParts = username.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            favoriteService.removeFavorite(firstName, lastName, busId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
