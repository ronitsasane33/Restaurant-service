package com.tmc.reservation.controller;

import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.service.RestaurantTablesService;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tables")
public class TableController {

    private final RestaurantTablesService tableService;

    public TableController(RestaurantTablesService tableService) {
        this.tableService = tableService;
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
