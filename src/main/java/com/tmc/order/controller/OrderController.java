package com.tmc.order.controller;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.service.OrderProducerService;
import com.tmc.order.service.OrderService;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary = "Get order by ID", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping(value = "{id}")
    public Response<OrderDto> getOrderById(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getOrderById(id)))
                .build();
    }

    @Operation(summary = "Get all Orders", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping()
    public Response<List<OrderDto>> getAllOrders(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "3") Integer pageSize){
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrders(pageNumber, pageSize))
                .build();
    }

    @Operation(summary = "Get Orders by Restaurant", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping(value = "/restaurant/{restaurantId}")
    public Response<List<OrderDto>> getAllOrdersByRestaurant(@PathVariable("restaurantId") String restaurantId) {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrdersByRestaurant(restaurantId))
                .build();
    }

    @Operation(summary = "Place new order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PostMapping
    public Response<String> placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Order got processed in Kafka")
                .build()
                : Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Order failed to load")
                .build();
    }

    @Operation(summary = "Cancel order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PutMapping(value = "/cancel/{id}")
    public Response<OrderDto> cancelOrder(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.cancelOrder(id))
                .build();
    }

    @Operation(summary = "Update the order", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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
