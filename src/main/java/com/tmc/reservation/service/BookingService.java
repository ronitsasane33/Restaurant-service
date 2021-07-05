package com.tmc.reservation.service;

import com.tmc.reservation.dto.BookingDto;

import java.util.List;

public interface BookingService {
    boolean createBooking(BookingDto bookingDto);
    List<BookingDto> getAllFutureBookings();
//    public List<BookingDto> getAllFutureBookingsRestaurant(String id);
    List<BookingDto> getAllBookingsByRestaurant(String id);
    List<BookingDto> getAllBookings();
    BookingDto getBookingById(String id);
    BookingDto updateBooking(String id, BookingDto bookingDto);
    BookingDto deleteBooking(String id);
    List<BookingDto> getAllScheduledBookingsByRestaurant(String id);
}
