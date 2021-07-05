package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.restaurant.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "{id}")
    public Response<MenuDto> getMenuById(@PathVariable String id){
        log.info("Getting Menu by restaurant Id: {} MenuController", id);
        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getMenuById(id))
                .build();
    }

    @GetMapping("/restaurant/{restaurantName}")
    public Response<List<MenuDto>> getAllMenusOfCurrentRestaurant(@PathVariable String restaurantName){
        log.info("Getting Menu by restaurant name: {} MenuController", restaurantName);
        return Response.<List<MenuDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenusOfCurrentRestaurant(restaurantName))
                .build();
    }

    @GetMapping()
    public Response<List<MenuDto>> getAllMenus(){
        log.info("Getting all menus in system, MenuController");
        return Response.<List<MenuDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenus())
                .build();
    }

    @PostMapping()
    public Response<String> createMenu(
            @RequestBody MenuDto menuDto){
        log.info("Creating Menu, MenuController");
        return menuService.createMenu(menuDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Menu Successfully Added ")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Menu Failed to add ")
                .build();
    }

    @PutMapping(value = "/items/{menuId}")
    public Response<MenuDto> addNewItemInMenu(
            @PathVariable("menuId") String menuId,
            @RequestBody FoodItemDto foodItemDto) {
        log.warn("Adding new item to menu: {}, menuController", menuId);
        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.addNewItemInMenu(menuId, foodItemDto))
                .build();
    }

    @PutMapping(value = "/items/{menuId}/{foodItemId}")
    public Response<MenuDto> removeItemInMenu(
            @PathVariable("menuId") String menuId,
            @PathVariable("foodItemId") String foodItemId) {
        log.warn("Removing item from menu: {}, menuController", menuId);
        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.removeItemInMenu(menuId, foodItemId))
                .build();
    }

    @DeleteMapping(value = "{id}")
    public Response<MenuDto> deleteMenu(@PathVariable("id") String id){
        log.warn("Deleting the Menu: {}, MenuController", id);

        return Response.<MenuDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.deleteMenu(id))
                .build();
    }
}
