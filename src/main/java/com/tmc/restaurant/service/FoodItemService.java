package com.tmc.restaurant.service;

import com.tmc.restaurant.dto.FoodItemDto;

import java.util.List;

public interface FoodItemService {
    List<FoodItemDto> getAllFoodItems();
    boolean createFoodItem(FoodItemDto foodItemDto);
}
