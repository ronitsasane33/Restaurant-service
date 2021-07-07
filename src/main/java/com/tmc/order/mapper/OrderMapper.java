package com.tmc.order.mapper;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.model.entity.Order;
import com.tmc.restaurant.mapper.FoodItemMapper;
import com.tmc.restaurant.mapper.RestaurantMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * MapStruct automatically does mapping
 * from the interface we created here
 * in resource folder
 */
@Mapper(componentModel = "spring", uses = {BillingMapper.class, FoodItemMapper.class, RestaurantMapper.class})
public interface OrderMapper {
    OrderDto toOderDTO(Order order);
    Order ToOrder(OrderDto orderDTO);
    List<OrderDto> toOrderDTOs(List<Order> orders);
    List<Order> toOrders(List<OrderDto> orderDTOs);
}
