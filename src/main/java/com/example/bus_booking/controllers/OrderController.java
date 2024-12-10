package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.Orders;
import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Orders>> getOrdersByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(orderService.getOrdersByClientId(clientId));
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Orders>> getOrdersByBusId(@PathVariable Long busId) {
        return ResponseEntity.ok(orderService.getOrdersByBusId(busId));
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
