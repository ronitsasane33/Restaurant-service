package com.tmc.reservation.controller;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.service.BookingService;
import com.tmc.reservation.service.impl.BookingServiceImpl;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get booking by ID", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping(value = "{id}")
    public Response<BookingDto> getBookingById(@PathVariable String id) {
        log.info("Getting Booking: {} Booking Controller", id);
        return Response.<BookingDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.getBookingById(id))
                .build();
    }

    @Operation(summary = "Get all bookings", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get booking by restaurant", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get all future/scheduled booking of restaurant", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get all scheduled/future bookings", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Create new booking", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PostMapping
    public Response<String> createBooking(@RequestBody BookingDto bookingDto) {
        log.info("creating booking, booking controller");
        return bookingService.createBooking(bookingDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Booking Successful")
                .build()
                : Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Booking Failed")
                .build();
    }

    @Operation(summary = "Update the booking", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Cancel the booking", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @DeleteMapping(value = "/{id}")
    public Response<BookingDto> deleteBooking(@PathVariable("id") String id) {
        log.info("Deleting booking : {}, Booking Controller", id);
        return Response.<BookingDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(bookingService.deleteBooking(id))
                .build();
    }
}
