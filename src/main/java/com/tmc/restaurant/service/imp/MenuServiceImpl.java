package com.tmc.restaurant.service.imp;

import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.entity.Menu;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.mapper.MenuMapper;
import com.tmc.restaurant.respository.MenuRespository;
import com.tmc.restaurant.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRespository menuRespository;
    private final MenuMapper menunMapper;

    public MenuServiceImpl(MenuRespository menuRespository, MenuMapper menunMapper) {
        this.menuRespository = menuRespository;
        this.menunMapper = menunMapper;
    }

    @Override
    public MenuDto getMenuById(String id) {
        return menunMapper.toMenuDto(menuRespository.findById(id).get());
    }

    @Override
    public List<MenuDto> getAllMenusOfCurrentRestaurant(String restaurantName) {
        return null;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        List<MenuDto> menuDtos = menunMapper.toMenuDtos((List<Menu>) menuRespository.findAll());
        if(menuDtos.size()>0){
            return menuDtos;
        }
        throw new RestaurantServiceException("No Menus found");
    }

    @Override
    public boolean createMenu(MenuDto menuDto) {
        Menu menu = menunMapper.toMenu(menuDto);
        menuRespository.save(menu);
        return Boolean.TRUE;
    }
}
