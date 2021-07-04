package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.entity.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper
{
    FoodItemDto toFoodItemDto(FoodItem foodItem);
    FoodItem toFoodItem(FoodItemDto foodItemDto);
}
