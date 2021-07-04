package com.tmc.restaurant.service;

import com.tmc.restaurant.dto.MenuDto;

import java.util.List;

public interface MenuService {
    MenuDto getMenuById(String id);
    List<MenuDto> getAllMenusOfCurrentRestaurant(String restaurantName);
    List<MenuDto> getAllMenus();
    boolean createMenu(MenuDto menuDto);
}
