package com.example.bus_booking.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BusRepository {
    List<Bus> findByModel(String model);

    @Query("Select bus from Bus bus where bus.availableStart <= :start_time and bus.availableEnd >= :end_time")
    List<Bus> findAvailableBuses(@Param("start_time") String startDate, @Param("end_time") String endDate);
}
