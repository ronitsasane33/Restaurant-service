package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.service.FoodItemService;
import com.tmc.restaurant.dto.FoodItemDto;
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
@RequestMapping(value = "/fooditems")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @Operation(summary = "Get food item by ID", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping(value = "{id}")
    public Response<FoodItemDto> getFoodItemById(@PathVariable String id){
        log.info("Getting Food Item: {} FoodItemController", id);
        return Response.<FoodItemDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(foodItemService.getFoodItemById(id))
                .build();
    }

    @Operation(summary = "Get all food Items", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping
    public Response<List<FoodItemDto>> getAllFoodItems() {
        log.info("Get all FoodItems, Order-service");
        return Response.<List<FoodItemDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(foodItemService.getAllFoodItems())
                .build();
    }

    @Operation(summary = "Create food item", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PostMapping
    public Response<String> createFoodItem(@RequestBody FoodItemDto foodItemDto) {
        log.info("create new FoodItems, Order-service");
        return foodItemService.createFoodItem(foodItemDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("New Food Item added")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Food Item failed to add")
                .build();
    }

    @Operation(summary = "Update Food Item", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @PutMapping(value = "/{foodItemId}")
    public Response<FoodItemDto> updateFoodItem(
            @PathVariable("foodItemId") String foodItemId,
            @RequestBody FoodItemDto foodItemDto) {
        log.warn("Updating food item : {}, FoodItemController", foodItemId);
        return Response.<FoodItemDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(foodItemService.updateFoodItem(foodItemId, foodItemDto))
                .build();
    }

    @Operation(summary = "Delete food item", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @DeleteMapping(value = "/{foodItemId}")
    public Response<FoodItemDto> deleteFoodItem(@PathVariable("foodItemId") String foodItemId){
        log.warn("Deleting the Food Item: {}, FoodItemController", foodItemId);
        return Response.<FoodItemDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(foodItemService.deleteFoodItem(foodItemId))
                .build();
    }
}
