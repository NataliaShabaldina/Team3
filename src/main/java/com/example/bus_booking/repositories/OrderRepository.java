package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT Orders FROM Orders WHERE client = :clientId")
    List<Orders> findOrder(@Param("clientId") Long clientId);

    @Query("select orderStatus FROM Orders WHERE id = :orderId")
    String findStatus(@Param("orderId") Long orderId);

    @Query("Select Orders from Orders where bus  = :busId")
    List<Orders> findOrderBus(@Param("busId") Long busId);
}
