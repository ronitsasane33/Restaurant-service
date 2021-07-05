package com.tmc.restaurant.dto;

import com.tmc.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private String menuId;
    private String menuType;
    private List<FoodItemDto> foodItems;
    private RestaurantDto restaurant;
}
