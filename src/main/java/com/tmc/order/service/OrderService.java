package com.tmc.order.service;

import com.tmc.order.dto.OrderDto;
import java.util.List;

public interface OrderService {
    Boolean placeOrder(OrderDto order);
    List<OrderDto> getAllOrders(int pageNumber, int pageSize);
    OrderDto getOrderById(String eid);
    OrderDto updateOrder(String eid, OrderDto orderDTO);
    OrderDto cancelOrder(String id);
    List<OrderDto> getAllOrdersByRestaurant(String restaurantId);
}
