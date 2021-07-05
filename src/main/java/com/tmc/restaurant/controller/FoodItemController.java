package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.service.FoodItemService;
import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
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
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Food Item failed to add")
                .build();
    }

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
