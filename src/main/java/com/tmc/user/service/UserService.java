package com.tmc.user.service;

import com.tmc.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(String id);

    List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize);

    boolean createUser(UserDto userDto);
}
