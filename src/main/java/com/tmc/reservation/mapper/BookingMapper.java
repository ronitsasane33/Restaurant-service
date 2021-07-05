package com.tmc.reservation.mapper;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.model.Booking;
import com.tmc.restaurant.mapper.RestaurantMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RestaurantTableMapper.class, RestaurantMapper.class})
public interface BookingMapper {
    BookingDto toBookingDto(Booking booking);
    Booking toBooking(BookingDto bookingDto);
    List<BookingDto> toBookingDtos(List<Booking> bookings);
    List<Booking> toBookings(List<BookingDto> bookingDtos);
}
