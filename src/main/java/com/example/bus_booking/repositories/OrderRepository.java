package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT id FROM Orders WHERE client_id = :clientId")
    List<Orders> findOrder(@Param("clientId") Long clientId);

    @Query("select order_status FROM Order WHERE id = :orderId")
    String findStatus(@Param("orderId") Long orderId);

    @Query("Select id from orders where bus_id")
    List<Orders> findOrderBus(@Param("busId") Long busId);
}
