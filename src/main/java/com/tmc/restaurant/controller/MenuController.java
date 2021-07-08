package com.tmc.restaurant.controller;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.restaurant.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get menu by ID", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get menus of a Restaurant ", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Get all menus", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    @GetMapping()
    public Response<List<MenuDto>> getAllMenus(
        @RequestParam(defaultValue = "0") Integer pageNumber,
        @RequestParam(defaultValue = "3") Integer pageSize){
        log.info("Getting all menus in system, MenuController");
        return Response.<List<MenuDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenus(pageNumber, pageSize))
                .build();
    }

    @Operation(summary = "Create new menu", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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
                        .statusMessage(StatusMessage.INTERNAL_ERROR.name()).build())
                .data("Menu Failed to add ")
                .build();
    }

    @Operation(summary = "Add new food item into menu", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Remove food item from menu", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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

    @Operation(summary = "Delete menu", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
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
