package com.tmc.restaurant.controller;

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

    /**
     * ACCESS: level 3 (Admin)
     * Returns all the food items from
     * all the restaurants
     * @return
     */
    @GetMapping
    public Response<List<FoodItemDto>> getAllFoodItems() {
        log.info("Inside Get all FoodItems from Order-service");
        return Response.<List<FoodItemDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(foodItemService.getAllFoodItems())
                .build();
    }

    @PostMapping
    public Response<String> createFoodItem(@RequestBody FoodItemDto foodItemDto) {
        log.info("Inside create new FoodItems from Order-service");
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
}
