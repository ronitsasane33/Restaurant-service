package com.tmc.reservation.service;

import com.tmc.reservation.dto.RestaurantTableDto;

import java.util.List;

public interface RestaurantTablesService {
    boolean createRestaurantTable(RestaurantTableDto restaurantTableDto);
    boolean createBulkTables(int numberOfTables);
    List<RestaurantTableDto> getAllTables();
    List<RestaurantTableDto> getAllTablesByRestaurant(String id);
}
