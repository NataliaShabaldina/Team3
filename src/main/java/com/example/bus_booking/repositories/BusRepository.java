package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByModel(String model);

    @Query("Select bus_id from orders where buses.available_start <= :start_time and buses.available_end >= :end_time")
    List<Bus> findAvailableBuses(@Param("start_time") LocalDate startDate, @Param("end_time") LocalDate endDate);

    @Query("select avg_rating from buses where buses.id = :id")
    double findAvgRatingByBusId(@Param("id") Long id);
}
