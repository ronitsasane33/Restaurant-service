package com.tmc.restaurant.service.imp;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.entity.Menu;
import com.tmc.restaurant.entity.enums.FoodItemStatus;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.mapper.FoodItemMapper;
import com.tmc.restaurant.mapper.MenuMapper;
import com.tmc.restaurant.respository.FoodItemRespository;
import com.tmc.restaurant.respository.MenuRespository;
import com.tmc.restaurant.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    private final MenuRespository menuRespository;
    private final MenuMapper menuMapper;
    private final FoodItemMapper foodItemMapper;
    private final FoodItemRespository foodItemRespository;

    public MenuServiceImpl(MenuRespository menuRespository, MenuMapper menunMapper, FoodItemMapper foodItemMapper, FoodItemRespository foodItemRespository) {
        this.menuRespository = menuRespository;
        this.menuMapper = menunMapper;
        this.foodItemMapper = foodItemMapper;
        this.foodItemRespository = foodItemRespository;
    }

    @Override
    public MenuDto getMenuById(String id) {
        try {
            log.info("Getting Menu by id: {} , restaurantService", id);
            Optional<Menu> menu = menuRespository.findById(id);
            if (!menu.isPresent()) {
                throw new RestaurantServiceException("Menu with id" + id + "does not exist");
            }
            return menuMapper.toMenuDto(menu.get());
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public List<MenuDto> getAllMenusOfCurrentRestaurant(String restaurantId) {
        try {
            log.info("Getting all menus of restaurant {} , restaurantService", restaurantId);
            List<MenuDto> menuDtos = menuMapper
                    .toMenuDtos(menuRespository.findAllByRestaurantRestaurantId(restaurantId));
            if (menuDtos.size() > 0) {
                return menuDtos;
            }
            throw new RestaurantServiceException("No Menus found");
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public List<MenuDto> getAllMenus() {
        try {
            log.info("Getting all menus in system , restaurantService");
            List<MenuDto> menuDtos = menuMapper
                    .toMenuDtos((List<Menu>) menuRespository.findAll());
            if (menuDtos.size() > 0) {
                return menuDtos;
            }
            throw new RestaurantServiceException("No Menus found");
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public boolean createMenu(MenuDto menuDto) {
        log.info("Saving the Menu, MenuService");
        Menu menu = menuMapper.toMenu(menuDto);
        menuRespository.save(menu);
        return Boolean.TRUE;
    }

    @Override
    public MenuDto addNewItemInMenu(String menuId, FoodItemDto foodItemDto) {
        try {
            log.info("Adding new Food Item to the menu: {}, menuService", menuId);
            Optional<Menu> menuOptional = menuRespository.findById(menuId);
            if (!menuOptional.isPresent()) {
                throw new RestaurantServiceException("Menu with id" + menuId + "does not exist");
            } else if (foodItemDto.getFoodItemStatus() == FoodItemStatus.DEACTIVE) {
                throw new RestaurantServiceException("FoodItem " + foodItemDto.getFoodItemName()
                        + "is not Active");
            } else {
                Menu menu = menuOptional.get();
                FoodItem foodItem = foodItemMapper.toFoodItem(foodItemDto);
                List<FoodItem> foodItems = menu.getFoodItems();
                foodItems.add(foodItem);
                menu.setFoodItems(foodItems);
                menuRespository.save(menu);
                return menuMapper.toMenuDto(menu);
            }
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public MenuDto removeItemInMenu(String menuId, String foodItemId) {
        try {
            log.info("Removing the Food Item from menu: {}, menuService", menuId);
            Optional<Menu> menuOptional = menuRespository.findById(menuId);
            if (!menuOptional.isPresent()) {
                throw new RestaurantServiceException("Menu with id" + menuId + "does not exist");
            } else {
                Menu menu = menuOptional.get();
                List<FoodItem> foodItems = menu.getFoodItems();
                for (FoodItem foodItem : foodItems) {
                    if (foodItem.getFoodItemId().equals(foodItemId)) {
                        foodItems.remove(foodItem);
                        break;
                    }
                }
                menu.setFoodItems(foodItems);
                menuRespository.save(menu);
                return menuMapper.toMenuDto(menu);
            }
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public MenuDto deleteMenu(String id) {
        try {
            Optional<Menu> menu = menuRespository.findById(id);
            log.warn("Deleting the Menu: {} from restaurant {}, MenuService", id, menu.get().getRestaurant().getRestaurantName());
            if (!menu.isPresent()) {
                throw new RestaurantServiceException("Menu with id" + id + "does not exist");
            } else {
                menuRespository.delete(menu.get());
                return menuMapper.toMenuDto(menu.get());
            }
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }
}
