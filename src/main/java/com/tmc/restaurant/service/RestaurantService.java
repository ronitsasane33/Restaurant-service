package com.tmc.restaurant.service;

import com.tmc.restaurant.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto getRestaurantById(String id);
    boolean createRestaurant(RestaurantDto restaurantDto);
    List<RestaurantDto> getAllRestaurants();
}
