package com.tmc.reservation.controller;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.service.BookingService;
import com.tmc.reservation.service.impl.BookingServiceImpl;
import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "{id}")
    public Response<BookingDto> getBookingById(@PathVariable String id){
        log.info("Getting Booking: {} Booking Controller", id);
        return Response.<BookingDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getBookingById(id))
                .build();
    }

    @GetMapping
    public Response<List<BookingDto>> getAllBookings() {
        log.info("Get all the bookings");
        return Response.<List<BookingDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getAllBookings())
                .build();
    }

    @GetMapping(value = "/restaurant/{id}")
    public Response<List<BookingDto>> getAllBookingsByRestaurant(@PathVariable("id") String id) {
        log.info("Get all the bookings by Restaurant {}", id);
        return Response.<List<BookingDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getAllBookingsByRestaurant(id))
                .build();
    }

    @GetMapping(value = "scheduled/restaurant/{id}")
    public Response<List<BookingDto>> getAllScheduledBookingsByRestaurant(@PathVariable("id") String id) {
        log.info("Get all the scheduled bookings by Restaurant {}", id);
        return Response.<List<BookingDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getAllScheduledBookingsByRestaurant(id))
                .build();
    }

    @GetMapping(value = "/scheduled")
    public Response<List<BookingDto>> getAllFutureBookings() {
        log.info("Get all the scheduled bookings");
        return Response.<List<BookingDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getAllFutureBookings())
                .build();
    }

    @PostMapping
    public Response<String> createBooking(@RequestBody BookingDto bookingDto) {
        log.info("creating booking, booking controller");
        return bookingService.createBooking(bookingDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Booking Successful")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Booking Failed")
                .build();
    }

    @PutMapping(value = "/{id}")
    public Response<BookingDto> updateBooking(
            @PathVariable("id") String id,
            @RequestBody BookingDto BookingDto) {
        log.info("Updating booking : {}, Booking Controller", id);
        return Response.<BookingDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.updateBooking(id, BookingDto))
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public Response<BookingDto> deleteBooking(@PathVariable("id") String id){
        log.info("Deleting booking : {}, Booking Controller", id);
        return Response.<BookingDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.deleteBooking(id))
                .build();
    }
}
