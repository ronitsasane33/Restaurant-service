package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.UserDto;
import com.tmc.restaurant.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",  uses = {FoodItemMapper.class})
public interface UserMapping {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
