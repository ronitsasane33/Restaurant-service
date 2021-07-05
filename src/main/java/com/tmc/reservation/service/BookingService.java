package com.tmc.reservation.service;

import com.tmc.reservation.dto.BookingDto;

import java.util.List;

public interface BookingService {
    boolean createBooking(BookingDto bookingDto);

    List<BookingDto> getAllFutureBookings();

    List<BookingDto> getAllBookings();
}
