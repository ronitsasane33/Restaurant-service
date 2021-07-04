package com.tmc.order.dto;

import com.tmc.order.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private String customerId;
    private OrderStatus orderStatus;
    private Timestamp creationTime;
//    private List<FoodItem> foodItems;
    private BillingDto billing;
}
