package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.restaurant.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("restaurants/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/{restaurantName}/{id}")
    public Response<MenuDto> getMenuById(@PathVariable String restaurantName, @PathVariable String id){
        log.info("inside getMenuById of MenuController");
        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getMenuById(id))
                .build();
    }

    @GetMapping("/{restaurantName}")
    public Response<List<MenuDto>> getAllMenusOfCurrentRestaurant(@PathVariable String restaurantName){
        return Response.<List<MenuDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenusOfCurrentRestaurant(restaurantName))
                .build();
    }

    @GetMapping()
    public Response<List<MenuDto>> getAllMenus(){
        return Response.<List<MenuDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenus())
                .build();
    }

    @PostMapping("/{restaurantName}")
    public Response<String> createMenu(@PathVariable String restaurantName, @RequestBody MenuDto menuDto){
        return menuService.createMenu(menuDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Menu for restaurant" + restaurantName +" Successfully Added ")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Menu for restaurant" + restaurantName +" Failed to add ")
                .build();
    }
}
