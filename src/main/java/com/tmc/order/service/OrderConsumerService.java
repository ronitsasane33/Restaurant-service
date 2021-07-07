package com.tmc.order.service;

import com.tmc.order.dto.OrderDto;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public interface OrderConsumerService {
    public void consumeOrdersData( long offset, String topic, Integer partition, String key, OrderDto orderDto);
}
