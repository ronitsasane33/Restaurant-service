package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.MenuDto;
import com.tmc.restaurant.entity.Menu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {FoodItemMapper.class})
public interface MenuMapper {
    MenuDto toMenuDto(Menu menu);
    Menu toMenu(MenuDto menuDto);
    List<Menu> toMenus(List<MenuDto> menuDtos);
    List<MenuDto> toMenuDtos(List<Menu> menu);
}
