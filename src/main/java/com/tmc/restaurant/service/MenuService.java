package com.tmc.restaurant.service;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.entity.FoodItem;

import java.util.List;

public interface MenuService {
    MenuDto getMenuById(String id);
    List<MenuDto> getAllMenusOfCurrentRestaurant(String restaurantName);
    List<MenuDto> getAllMenus(int pageNumber, int pageSize);
    boolean createMenu(MenuDto menuDto);
    MenuDto deleteMenu(String id);
    MenuDto addNewItemInMenu(String menuId, FoodItemDto foodItemDto);
    MenuDto removeItemInMenu(String menuId, String foodItemId);
}
