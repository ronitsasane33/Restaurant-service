package com.tmc.reservation.dto;

import com.tmc.reservation.model.enums.TableStatus;
import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableDto {
    private String tableId;
    private int tableNumber;
    private TableStatus tableStatus;
    private int capacity;
    private RestaurantDto restaurant;
}
