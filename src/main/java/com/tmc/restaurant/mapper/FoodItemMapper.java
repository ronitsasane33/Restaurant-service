package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.entity.FoodItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodItemMapper
{
    FoodItemDto toFoodItemDto(FoodItem foodItem);
    FoodItem toFoodItem(FoodItemDto foodItemDto);
    List<FoodItemDto> toFoodItemDtos(List<FoodItem> foodItems);
    List<FoodItem> toFoodItems(List<FoodItemDto> foodItemDtos);
}
