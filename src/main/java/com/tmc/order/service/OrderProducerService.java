package com.tmc.order.service;

import com.tmc.order.dto.OrderDto;

public interface OrderProducerService {
    public boolean placeOrder(OrderDto orderDTO);
}
