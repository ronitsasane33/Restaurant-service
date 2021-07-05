package com.tmc.reservation.service;

import com.tmc.reservation.dto.RestaurantTableDto;

public interface RestaurantTablesService {
    boolean createRestaurantTable(RestaurantTableDto restaurantTableDto);
    boolean createBulkTables(int numberOfTables);
}
