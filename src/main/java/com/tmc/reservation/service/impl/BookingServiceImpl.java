package com.tmc.reservation.service.impl;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.mapper.BookingMapper;
import com.tmc.reservation.model.Booking;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.reservation.repository.BookingRepository;
import com.tmc.reservation.repository.RestaurantTableRepository;
import com.tmc.reservation.service.BookingService;
import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.exception.ReservationServiceException;
import com.tmc.restaurant.mapper.RestaurantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final RestaurantMapper restaurantMapper;
    private final BookingRepository bookingRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public BookingServiceImpl(BookingMapper bookingMapper,
                              RestaurantMapper restaurantMapper, BookingRepository bookingRepository,
                              RestaurantTableRepository restaurantTableRepository) {
        this.bookingMapper = bookingMapper;
        this.restaurantMapper = restaurantMapper;
        this.bookingRepository = bookingRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @Override
    public BookingDto getBookingById(String id) {
        try{
            log.info("Getting Booking by id: {} , Booking Service", id);
            Optional<Booking> booking = bookingRepository.findById(id);
            if (!booking.isPresent()) {
                throw new ReservationServiceException("Booking with id" + id + "does not exist");
            }
            return bookingMapper.toBookingDto(booking.get());
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public List<BookingDto> getAllBookings() {
        try{
            log.info("Getting All bookings of all restaurants , Booking Service");
            List<BookingDto> bookingDtos = bookingMapper.toBookingDtos((List<Booking>) bookingRepository.findAll());
            if (bookingDtos.size() > 0) {
                return bookingDtos;
            }
            throw new ReservationServiceException("No bookings found");
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public List<BookingDto> getAllFutureBookings() {
        try{
            log.info("Getting all scheduled bookings , Booking Service");
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            List<BookingDto> bookingDtos = bookingMapper.
                    toBookingDtos(bookingRepository.findAllByBookingStartTimeAfter(currentTime));
            if (bookingDtos.size() > 0) {
                return bookingDtos;
            }
            throw new ReservationServiceException("No bookings found");
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public List<BookingDto> getAllScheduledBookingsByRestaurant(String id) {
        try{
            log.info("Getting all scheduled bookings by restaurant: {} , Booking Service", id);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            List<BookingDto> bookingDtos = bookingMapper.
                    toBookingDtos(bookingRepository.
                            findAllByRestaurantTableRestaurantRestaurantIdAndBookingStartTimeAfter(id, currentTime));
            if (bookingDtos.size() > 0) {
                return bookingDtos;
            }
            throw new ReservationServiceException("No bookings found");
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public List<BookingDto> getAllBookingsByRestaurant(String id) {
        try{
            log.info("Getting all bookings by restaurant: {} , Booking Service", id);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            List<BookingDto> bookingDtos = bookingMapper.
                    toBookingDtos(bookingRepository.findAllByRestaurantTableRestaurantRestaurantId(id));
            if (bookingDtos.size() > 0) {
                return bookingDtos;
            }
            throw new ReservationServiceException("No bookings found");
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean createBooking(BookingDto bookingDto) {
        try{
            log.info("Creating Booking : {} , Booking Service", bookingDto.getBookingId());
            Timestamp bookingStart = bookingDto.getBookingStartTime();
            Timestamp bookingEnd = bookingDto.getBookingStartTime();
            bookingEnd.setTime(bookingEnd.getTime() + ((60 * 60) * 1000));
            String restaurantId = bookingDto.getRestaurant().getRestaurantId();
            RestaurantTable table = getAvailbleTableAtRequiredTime(bookingStart, bookingEnd, restaurantId);

            if (table != null) {
                Booking booking = saveBooking(table, bookingStart, bookingEnd,
                        bookingDto.getCustomerId(), bookingDto.getRestaurant());
            } else {
                throw new ReservationServiceException("Table not available at this time");
            }
            return Boolean.TRUE;
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public BookingDto updateBooking(String id, BookingDto bookingDto) {
        try{
            log.info("Updating Booking by id: {} , Booking Service", id);
            Optional<Booking> bookingOptional = bookingRepository.findById(id);
            if (bookingDto.getBookingStartTime() != null) {
                Timestamp bookingStart = bookingDto.getBookingStartTime();
                Timestamp bookingEnd = bookingDto.getBookingStartTime();
                bookingEnd.setTime(bookingEnd.getTime() + ((60 * 60) * 1000));
                String restaurantId = bookingDto.getRestaurant().getRestaurantId();
                RestaurantTable table = getAvailbleTableAtRequiredTime(bookingStart, bookingEnd, restaurantId);
                if (table == null) {
                    throw new ReservationServiceException("Table not available at this time");
                }
            }
            if (!bookingOptional.isPresent()) {
                throw new ReservationServiceException("Booking with id" + id + "does not exist");
            } else {
                Booking booking = bookingOptional.get();
                if (bookingDto.getCustomerId() != null) booking.setCustomerId(bookingDto.getCustomerId());
                if (bookingDto.getBookingStartTime() != null) booking.setBookingStartTime(bookingDto.getBookingStartTime());
                if (bookingDto.getBookingEndTime() != null) booking.setBookingEndTime(bookingDto.getBookingEndTime());
                bookingRepository.save(booking);
                return bookingMapper.toBookingDto(booking);
            }
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    @Override
    public BookingDto deleteBooking(String id) {
        try{
            log.info("Deleting Booking by id: {} , Booking Service", id);
            Optional<Booking> booking = bookingRepository.findById(id);
            if (!booking.isPresent()) {
                throw new ReservationServiceException("Booking with id" + id + "does not exist");
            } else {
                bookingRepository.delete(booking.get());
                return bookingMapper.toBookingDto(booking.get());
            }
        }catch (Exception e){
            throw new ReservationServiceException(e.getMessage());
        }
    }

    /**
     * Method to find the
     * table at
     * required time
     */
    private RestaurantTable getAvailbleTableAtRequiredTime(Timestamp bookingStart, Timestamp bookingEnd, String id) {
        RestaurantTable table = restaurantTableRepository
                .getAvailableTables(bookingStart, bookingEnd, id)
                .stream().findFirst().orElse(null);
        return table;
    }

    /**
     * Method to
     * persist the booking
     */
    private Booking saveBooking(RestaurantTable table, Timestamp bookingStart,
                                Timestamp bookingEnd, String customerId, RestaurantDto restaurant) {
        Booking booking = new Booking();
        booking.setBookingStartTime(bookingStart);
        booking.setBookingEndTime(bookingEnd);
        booking.setRestaurantTable(table);
        booking.setCustomerId(customerId);
        booking.setRestaurant(restaurantMapper.toRestaurant(restaurant));
        return bookingRepository.save(booking);
    }
}