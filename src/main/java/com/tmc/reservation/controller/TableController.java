package com.tmc.reservation.controller;

import com.tmc.reservation.dto.BookingDto;
import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.service.RestaurantTablesService;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get all tables", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get all tables by restaurant", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Create new restaurant table", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Restaurant table failed to add")
                .build();
    }

    @Operation(summary = "Creating tables in Bulk", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PostMapping(value = "/{numberOfTables}")
    public Response<String> createBulkTables(
            @PathVariable("numberOfTables") int numberOfTables) {
        return tableService.createBulkTables(numberOfTables) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Restaurant table added")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Restaurant table failed to add")
                .build();
    }
}
