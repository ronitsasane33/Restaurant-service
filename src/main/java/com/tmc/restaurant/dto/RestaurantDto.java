package com.tmc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tmc.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private String restaurantId;
    private String restaurantName;
    private AddressDto address;
}
