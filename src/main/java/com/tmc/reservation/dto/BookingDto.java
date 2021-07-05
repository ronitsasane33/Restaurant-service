package com.tmc.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private String bookingId;
    private String customerId;
    private Timestamp bookingEndTime;
    private Timestamp bookingStartTime;
    private RestaurantTableDto restaurantTable;
}
