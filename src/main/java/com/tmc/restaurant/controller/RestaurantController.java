package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.restaurant.service.RestaurantService;
import com.tmc.restaurant.service.imp.RestaurantServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "{id}")
    public Response<RestaurantDto> getRestuarantById(@PathVariable String id){
        return Response.<RestaurantDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.getRestaurantById(id))
                .build();
    }

    @GetMapping()
    public Response<List<RestaurantDto>> getAllRestuarants(){
        return Response.<List<RestaurantDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.getAllRestaurants())
                .build();
    }

    @PostMapping()
    public Response<String> createRestaurant(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.createRestaurant(restaurantDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Restaurant Successfully Added ")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Restaurant failed to add")
                .build();
    }
}
