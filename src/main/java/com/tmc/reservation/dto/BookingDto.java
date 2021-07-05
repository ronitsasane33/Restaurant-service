package com.tmc.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private String bookingId;
    private String customerId;
    private Timestamp bookingStartTime;
    private Timestamp bookingEndTime;
    private RestaurantTableDto restaurantTable;
}
