package com.tmc.reservation.repository;

import com.tmc.reservation.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, String> {

    List<Booking> findAllByBookingStartTimeAfter(@Param("startTime") Timestamp startTime);

    List<Booking> findAllByRestaurantTableRestaurantRestaurantId(@Param("id") String id);

    List<Booking> findAllByRestaurantTableRestaurantRestaurantIdAndBookingStartTimeAfter(
            @Param("id") String id,
            @Param("startTime") Timestamp startTime );

}
