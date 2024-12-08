package com.example.bus_booking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusController {
    @GetMapping("/bus")
    public String bus() {
        return "entities/bus";
    }
}
