package com.example.bus_booking.services;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.Orders;
import com.example.bus_booking.entities.PaymentCallBack;
import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.enums.PaymentStatusEnum;
import com.example.bus_booking.repositories.BusRepository;
import com.example.bus_booking.repositories.ClientRepository;
import com.example.bus_booking.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final BusRepository busRepository;


    public List<Orders> getClientOrders(String firstName, String lastName) {
        return orderRepository.findByClientName(firstName,lastName);
    }

    public void createOrder(Long clientId, Long busId, int seatCount) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new IllegalArgumentException("Автобус не найден"));

        if (bus.getAvailableSeats() < seatCount) {
            throw new IllegalStateException("Недостаточно свободных мест");
        }

        bus.setAvailableSeats(bus.getAvailableSeats() - seatCount);

        Orders order = new Orders(client, bus, seatCount);
        orderRepository.save(order);
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

    public void updateOrderStatus(PaymentCallBack paymentCallBack) {
        Orders order = orderRepository.findById(paymentCallBack.getOrderId())
                        .orElseThrow(() -> new IllegalArgumentException("Заказ с ID " + paymentCallBack.getOrderId() + " не найден"));
        if (paymentCallBack.isSuccess()) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setPaymentStatus(PaymentStatusEnum.PAID);
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setPaymentStatus(PaymentStatusEnum.UNPAID);
        }

        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
