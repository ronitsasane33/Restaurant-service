package com.tmc.order.service.kafka;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.service.OrderConsumerService;
import com.tmc.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumerServiceImpl implements OrderConsumerService {

    public OrderService orderService;

    @Autowired
    public OrderConsumerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "orderDtoKafkaListenerContainerFactory",
            topics = {"${kafka.topic.name.california}", "${kafka.topic.name.newyork}",
                      "${kafka.topic.name.texas}", "${kafka.topic.name.illinois}",
                      "${kafka.topic.name.other}"},
            groupId = "${kafka.topic.name.groupId}")
    public void consumeOrdersData(@Header(KafkaHeaders.OFFSET) long offset,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                  @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                  OrderDto orderDto) {
        log.info("Order {} consumed from partition {} at topic {} and offset {}", key, partition, topic, offset);
        orderService.placeOrder(orderDto);
    }
}


