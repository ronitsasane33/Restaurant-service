package com.tmc.reservation.service.impl;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.mapper.BookingMapper;
import com.tmc.reservation.model.Booking;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.reservation.repository.BookingRepository;
import com.tmc.reservation.repository.RestaurantTableRepository;
import com.tmc.reservation.service.BookingService;
import com.tmc.restaurant.exception.RestaurantServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepository bookingRepository, RestaurantTableRepository restaurantTableRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @Transactional
    @Override
    public boolean createBooking(BookingDto bookingDto) {
        Timestamp bookingStart = bookingDto.getBookingStartTime();
        Timestamp bookingEnd = bookingDto.getBookingStartTime();
        bookingEnd.setTime(bookingEnd.getTime() + ((60 * 60)* 1000));
        RestaurantTable table = restaurantTableRepository
                .getAvailableTables(bookingStart, bookingEnd)
                .stream().findFirst().orElse(null);

        if (table != null) {
            Booking booking = saveBooking(table, bookingStart, bookingEnd, bookingDto.getCustomerId());
        } else {
            throw new RestaurantServiceException("Table not available at this time");
        }
        return Boolean.TRUE;
    }

    @Override
    public List<BookingDto> getAllFutureBookings() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<BookingDto> bookingDtos = bookingMapper.toBookingDtos((List<Booking>)bookingRepository.findAllByBookingStartTimeAfter(currentTime));
        if (bookingDtos.size() > 0) {
            return bookingDtos;
        }
        throw new RestaurantServiceException("No bookings found");
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<BookingDto> bookingDtos = bookingMapper.toBookingDtos((List<Booking>) bookingRepository.findAll());
        if (bookingDtos.size() > 0) {
            return bookingDtos;
        }
        throw new RestaurantServiceException("No bookings found");
    }

    private Booking saveBooking(RestaurantTable table, Timestamp bookingStart,
                                Timestamp bookingEnd, String customerId) {
        Booking booking = new Booking();
        booking.setBookingStartTime(bookingStart);
        booking.setBookingEndTime(bookingEnd);
        booking.setRestaurantTable(table);
        booking.setCustomerId(customerId);
        return bookingRepository.save(booking);
    }
}