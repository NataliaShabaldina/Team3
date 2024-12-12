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

    @Query("Select bus from Bus bus where bus.availableStart <= :start_time and bus.availableEnd >= :end_time")
    List<Bus> findAvailableBuses(@Param("start_time") LocalDateTime startDate, @Param("end_time") LocalDateTime endDate);

    @Query("select avg(bus.avgRating) from Bus bus")
    Double findAverageRating();

    @Query("select bus.avgRating from Bus bus where bus.id = :id")
    Double findAvgRatingByBusId(@Param("id") Long id);

    @Query("SELECT b FROM Bus b WHERE b.availableStart >= :start_time AND b.availableEnd <= :end_time " +
            "AND (:wifi IS NULL OR b.hasWifi = :wifi) " +
            "AND (:airConditioner IS NULL OR b.hasAirConditioning = :airConditioner) " +
            "AND (:minSeats IS NULL OR b.seatCount >= :minSeats)")
    List<Bus> findAvailableBusesWithFilters(@Param("start_time") LocalDateTime startDate,
                                            @Param("end_time") LocalDateTime endDate,
                                            @Param("wifi") Boolean wifi,
                                            @Param("airConditioner") Boolean airConditioning,
                                            @Param("minSeats") Integer minSeats);
}
