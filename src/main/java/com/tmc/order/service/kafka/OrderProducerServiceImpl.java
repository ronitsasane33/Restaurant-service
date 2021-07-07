package com.tmc.order.service.kafka;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.service.OrderProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class OrderProducerServiceImpl implements OrderProducerService {

    private final KafkaTemplate<String, OrderDto> orderDtoKafkaTemplate;

    @Value("${kafka.topic.name.california}")
    private String regionCa;

    @Value("${kafka.topic.name.newyork}")
    private String regionNy;

    @Value("${kafka.topic.name.texas}")
    private String regionTx;

    @Value("${kafka.topic.name.illinois}")
    private String regionIl;

    @Value("${kafka.topic.name.other}")
    private String regionOther;

    public OrderProducerServiceImpl(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.orderDtoKafkaTemplate = kafkaTemplate;
    }

    /**
     * Produce messages into Kafka
     */
    public boolean placeOrder(OrderDto orderDto) {
        log.info(String.format("Producing Orders: %s", orderDto));
        String restaurantState = orderDto.getRestaurant().getAddress().getState().toLowerCase();
        HashMap<String, String> stateMapping = new HashMap<>();
        stateMapping.put("new york", regionNy);
        stateMapping.put("california", regionCa);
        stateMapping.put("texas", regionTx);
        stateMapping.put("illinois", regionIl);
        log.info(" Order processing from topic {}", stateMapping.get(restaurantState));


        orderDtoKafkaTemplate.executeInTransaction(transaction -> {
            ListenableFuture<SendResult<String, OrderDto>> future = transaction.send(
                    stateMapping.getOrDefault(restaurantState, regionOther),
                    UUID.randomUUID().toString(),
                    orderDto);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDto>>() {
                @Override
                public void onSuccess(SendResult<String, OrderDto> result) {
                    RecordMetadata sentOrder = result.getRecordMetadata();
                    log.info("Produced order {} at offset {}", sentOrder.offset(), sentOrder.topic());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Unable to process order {} due to {}", orderDto, throwable.getMessage());
                }
            });
            return true;
        });
        return true;
    }
}
