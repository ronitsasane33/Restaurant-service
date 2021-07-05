package com.tmc.reservation.repository;

import com.tmc.reservation.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, String> {
}
