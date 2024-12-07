package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Bus;
import com.example.bus_booking.entities.Notifications;
import com.example.bus_booking.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT f.order FROM Orders f WHERE f.order.id = :orderId")
    List<Orders> findOrder(@Param("clientId") Long clientId);

    @Query("select f.order FROM Order f WHERE f.order_status = :order_status")
    String findStatus();

    @Query("Select bus from Bus bus where f.bus")
    List<Orders> findOrderBus();
}
