package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.client.id = :clientId")
    List<Orders> findOrder(@Param("clientId") Long clientId);

    @Query("select o.orderStatus FROM Orders o WHERE o.id = :orderId")
    String findStatus(@Param("orderId") Long orderId);

    @Query("Select o from Orders o where o.bus.id  = :busId")
    List<Orders> findOrderBus(@Param("busId") Long busId);
}
