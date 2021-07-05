package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.restaurant.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "{id}")
    public Response<RestaurantDto> getRestuarantById(@PathVariable String id) {
        log.info("Getting restaurants by id: {}, restaurantController", id);
        return Response.<RestaurantDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.getRestaurantById(id))
                .build();
    }

    @GetMapping(value = "/restaurant/{name}")
    public Response<RestaurantDto> getRestuarantByName(@PathVariable String name) {
        log.info("Getting restaurants by name: {}, restaurantController", name);
        return Response.<RestaurantDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.getRestaurantByName(name))
                .build();
    }

    @GetMapping()
    public Response<List<RestaurantDto>> getAllRestuarants(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "3") Integer pageSize) {
        log.info("Getting all the restaurants, restaurantController");
        if (pageNumber == null) {
            pageNumber = 0;
        }
        return Response.<List<RestaurantDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.getAllRestaurants(pageNumber, pageSize))
                .build();
    }

    @PostMapping()
    public Response<String> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        log.info("Creating new restaurant: {}", restaurantDto.getRestaurantName());
        return restaurantService.createRestaurant(restaurantDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Restaurant Successfully Added ")
                .build()
                : Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Restaurant failed to add")
                .build();
    }

    @PutMapping(value = "{id}")
    public Response<RestaurantDto> updateRestaurant(
            @PathVariable("id") String id,
            @RequestBody RestaurantDto restaurantDto) {
        log.warn("Deleting the restaurant: {}, RestaurantController", restaurantDto.getRestaurantName());
        return Response.<RestaurantDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.updateRestaurant(id, restaurantDto))
                .build();
    }

    @DeleteMapping(value = "{id}")
    public Response<RestaurantDto> deleteRestaurant(@PathVariable("id") String id){
        log.warn("Deleting the restaurant: {}, RestaurantController", id);

        return Response.<RestaurantDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(restaurantService.deleteRestaurant(id))
                .build();
    }
}
