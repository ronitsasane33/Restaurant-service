package com.tmc.reservation.service;

import com.tmc.reservation.dto.BookingDto;

public interface BookingService {
    boolean createBooking(BookingDto bookingDto);
}
