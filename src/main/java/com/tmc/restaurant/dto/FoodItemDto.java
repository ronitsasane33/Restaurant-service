package com.tmc.restaurant.dto;

import com.tmc.restaurant.entity.enums.FoodItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDto {
    private String foodItemId;
    private String foodItemName;
    private int foodItemPrice;
    private String foodItemDescription;
    private FoodItemStatus foodItemStatus;
}
