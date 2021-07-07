package com.tmc.user.service.impl;

import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.user.dto.UserDto;
import com.tmc.user.entity.User;
import com.tmc.user.mapper.UserMapper;
import com.tmc.user.repository.UserRespository;
import com.tmc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;
    private  final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRespository userRespository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRespository = userRespository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserById(String id) {
        log.info("Getting User by id: {} , UserService", id);
        Optional<User> user = userRespository.findById(id);
        if(!user.isPresent()){
            throw new RestaurantServiceException("User with id" + id + "does not exist");
        }
        return userMapper.toUserDto(user.get());
    }

    @Override
    public List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize) {
        log.info("Getting all the User, UserService");
        List<UserDto> userDtos = userMapper
                .toUserDtos(userRespository
                        .findAll(PageRequest.of(pageNumber, pageSize)).getContent());
        if (userDtos.size() > 0) {
            return userDtos;
        }
        throw new RestaurantServiceException("No Users found");
    }

    @Override
    public boolean createUser(UserDto userDto) {
        log.info("Saving the User, MenuService");
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRespository.save(user);
        return Boolean.TRUE;
    }
}
