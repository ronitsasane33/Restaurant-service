package com.tmc.restaurant.service;

import com.tmc.restaurant.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto getRestaurantById(String id);
    RestaurantDto getRestaurantByName(String name);
    boolean createRestaurant(RestaurantDto restaurantDto);
    List<RestaurantDto> getAllRestaurants(int pageNumber, int pageSize);
    RestaurantDto updateRestaurant(String id, RestaurantDto restaurantDto);
    RestaurantDto deleteRestaurant(String id);
}
