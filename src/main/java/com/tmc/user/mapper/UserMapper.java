package com.tmc.user.mapper;

import com.tmc.user.dto.UserDto;
import com.tmc.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
    List<UserDto> toUserDtos(List<User> users);
    List<User> toUser(List<UserDto> userDtos);
}
