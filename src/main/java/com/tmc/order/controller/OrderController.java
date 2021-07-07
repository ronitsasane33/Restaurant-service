package com.tmc.order.controller;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.service.OrderProducerService;
import com.tmc.order.service.OrderService;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProducerService orderProducerService;

    public OrderController(OrderService orderService, OrderProducerService orderProducerService) {
        this.orderService = orderService;
        this.orderProducerService = orderProducerService;
    }

    @GetMapping
    public Response<List<OrderDto>> getAllOrders() {
        log.info("Inside Get all orders from Order-service");
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrders())
                .build();
    }

    /**
     * Gets new order
     * Passes to orderProducerService
     */
    @PostMapping
    public Response<String> placeOrder(@RequestBody OrderDto orderDto) {
        return orderProducerService.placeOrder(orderDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Order got processed in Kafka")
                .build()
                : Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Order failed to load")
                .build();
    }

    @GetMapping(value = "/page/{pageNumber}")
    public Response<List<OrderDto>> getAllOrdersPagination(@PathVariable("pageNumber") int pagenumber) {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getOrderPage(pagenumber))
                .build();
    }


    @GetMapping(value = "{id}")
    public Response<OrderDto> getOrderById(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getOrderById(id)))
                .build();
    }

    @PutMapping(value = "/cancel/{id}")
    public Response<OrderDto> cancelOrder(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.cancelOrder(id))
                .build();
    }

    @PutMapping(value = "{id}")
    public Response<OrderDto> updateOrder(@PathVariable("id") String oid, @RequestBody OrderDto orderDto) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.updateOrder(oid, orderDto))
                .build();
    }
}
