package com.tmc.order.dto;

import com.tmc.order.model.enums.OrderStatus;
import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private String customerId;
    private OrderStatus orderStatus;
    private Timestamp creationTime;
    private List<FoodItemDto> foodItems;
    private BillingDto billing;
    private RestaurantDto restaurant;
}
