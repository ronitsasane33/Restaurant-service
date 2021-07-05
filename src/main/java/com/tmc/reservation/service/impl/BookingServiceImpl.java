package com.tmc.reservation.service.impl;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.mapper.BookingMapper;
import com.tmc.reservation.model.Booking;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.reservation.repository.BookingRepository;
import com.tmc.reservation.repository.TableRepository;
import com.tmc.reservation.service.BookingService;
import com.tmc.restaurant.exception.RestaurantServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final TableRepository tableRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepository bookingRepository, TableRepository tableRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
        this.tableRepository = tableRepository;
    }

    @Transactional
    @Override
    public boolean createBooking(BookingDto bookingDto) {
        final Timestamp bookingStart = bookingDto.getBookingStartTime();
        final Timestamp bookingEnd = bookingDto.getBookingStartTime();
        bookingEnd.setTime(bookingEnd.getTime() + ((60 * 60)* 1000));
        RestaurantTable table = tableRepository
                .getAvailableTables(bookingStart, bookingEnd)
                .stream().findFirst().orElse(null);

        if (table != null) {
            Booking booking = saveBooking(table, bookingStart, bookingEnd, bookingDto.getCustomerId());
        } else {
            throw new RestaurantServiceException("Table not available at this time");
//            throwErrorWithRecomemdedTime(bookingDto.getCustomers(), bookingStart);
        }
        return Boolean.TRUE;
    }

    private Booking saveBooking(final RestaurantTable table, final Timestamp bookingStart,
                                final Timestamp bookingEnd, final String customerId) {
        Booking booking = new Booking();
        booking.setBookingStartTime(bookingStart);
        booking.setBookingEndTime(bookingEnd);
        booking.setRestaurantTable(table);
        booking.setCustomerId(customerId);
        return bookingRepository.save(booking);
    }

//    private void throwErrorWithRecomemdedTime(final Integer customerCount, final LocalDateTime bookingStart) {
//        Booking closestBookingWithFreeSlot = bookingRepository.findClosestBookingToInputTime(bookingStart);
//        StringBuilder errorMsg = new StringBuilder();
//        errorMsg.append("Table not available for capacity: ").append(customerCount).append(".");
//        if (closestBookingWithFreeSlot != null) {
//            errorMsg.append(" Table available after " + closestBookingWithFreeSlot.getBookingEndTime() + ".");
//        }
//        throw new RestaurantServiceException(errorMsg.toString());
//    }
}

//    @Transactional
//    public Booking addBooking(final BookingDto bookingRequest) {
//        log.debug("Creating new booking for request: {}", bookingRequest);
////        validateBookingData(bookingRequest);
//        return bookingCreateService.createBooking(bookingRequest);
//    }
//}

//public class BookingCreateService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//    @Autowired
//    private TableRepository tableRepository;
//
//    public Booking createBooking(final BookingDto bookingRequest) {
//        Booking booking = null;
//        final LocalDateTime bookingStart = bookingRequest.getBookingStartTime();
//        final LocalDateTime bookingEnd = bookingRequest.getBookingStartTime().plusHours(1);
//        RestaurantTable table = tableRepository
//                .findAvailableTableWithAdequateSeatingCapacity(bookingStart, bookingEnd)
//                .stream().findFirst().orElse(null);
//
//        if (table != null) {
//            booking = saveBooking(table, bookingStart, bookingEnd, bookingRequest.getCustomerId());
//        } else {
//            throwErrorWithRecomemdedTime(bookingRequest.getCustomers(), bookingStart);
//        }
//        return booking;
//    }
//
//    private Booking saveBooking(final RestaurantTable table, final LocalDateTime bookingStart,
//                                final LocalDateTime bookingEnd, final String customerId) {
//        Booking booking = new Booking();
//        booking.setBookingStartTime(bookingStart);
//        booking.setBookingEndTime(bookingEnd);
//        booking.setTable(table);
//        booking.setCustomerId(customerId);
//        return bookingRepository.save(booking);
//    }
//
//    private void throwErrorWithRecomemdedTime(final Integer customerCount, final LocalDateTime bookingStart) {
//        Booking closestBookingWithFreeSlot = bookingRepository.findClosestBookingToInputTime(bookingStart);
//        StringBuilder errorMsg = new StringBuilder();
//        errorMsg.append("Table not available for capacity: ").append(customerCount).append(".");
//        if (closestBookingWithFreeSlot != null) {
//            errorMsg.append(" Table available after " + closestBookingWithFreeSlot.getBookingEndTime() + ".");
//        }
//        throw new RestaurantServiceException(errorMsg.toString());
//    }
//}