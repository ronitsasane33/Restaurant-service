package com.tmc.reservation.controller;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.service.RestaurantTablesService;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/tables")
public class TableController {

    private final RestaurantTablesService tableService;

    public TableController(RestaurantTablesService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public Response<List<RestaurantTableDto>> getAllTables() {
        log.info("Get all the Tables");
        return Response.<List<RestaurantTableDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(tableService.getAllTables())
                .build();
    }

    @GetMapping(value = "/restaurant/{id}")
    public Response<List<RestaurantTableDto>> getAllTablesByRestaurant(@PathVariable("id") String id) {
        log.info("Get all the bookings by Restaurant {}", id);
        return Response.<List<RestaurantTableDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(tableService.getAllTablesByRestaurant(id))
                .build();
    }

    @PostMapping
    public Response<String> createRestaurantTable(@RequestBody RestaurantTableDto restaurantTableDto) {
        return tableService.createRestaurantTable(restaurantTableDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Restaurant table added")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Restaurant table failed to add")
                .build();
    }
    @PostMapping(value = "/{numberOfTables}")
    public Response<String> createBulkTables(@PathVariable("numberOfTables") int numberOfTables) {
        return tableService.createBulkTables(numberOfTables) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Restaurant table added")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Restaurant table failed to add")
                .build();
    }
}
