package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.OrderRequest;
import com.example.bus_booking.entities.Orders;
import com.example.bus_booking.entities.PaymentCallBack;
import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.createOrder(orderRequest.getClientId(), orderRequest.getBusId(), orderRequest.getSeatCount());
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ создан");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/user/orders")
    public List<Orders> getClientOrders(@RequestParam String firstName, @RequestParam String lastName) {
        return orderService.getClientOrders(firstName, lastName);
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
        try {
            PaymentCallBack paymentCallBack = new PaymentCallBack();
            paymentCallBack.setOrderId(id);
            paymentCallBack.setSuccess(status == OrderStatus.COMPLETED);
            orderService.updateOrderStatus(paymentCallBack);

            Orders updatedOrders = orderService.getOrderById(id);
            return ResponseEntity.ok(updatedOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
