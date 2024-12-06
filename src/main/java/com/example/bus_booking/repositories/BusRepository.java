package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByModel(String model);

    @Query("Select bus from Bus bus where bus.availableStart <= :start_time and bus.availableEnd >= :end_time")
    List<Bus> findAvailableBuses(@Param("start_time") LocalDate startDate, @Param("end_time") LocalDate endDate);
}
