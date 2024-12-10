package com.example.bus_booking.services;

import com.example.bus_booking.entities.Orders;
import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Заказ с ID " + orderId + " не найден"));
    }

    public List<Orders> getOrdersByClientId(Long clientId) {
        return orderRepository.findOrder(clientId);
    }

    public List<Orders> getOrdersByBusId(Long busId) {
        return orderRepository.findOrderBus(busId);
    }

    public String getOrderStatus(Long orderId) {
        return orderRepository.findStatus(orderId);
    }

    public Orders updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Orders order = getOrderById(orderId);
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
