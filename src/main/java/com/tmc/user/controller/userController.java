package com.tmc.user.controller;

import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import com.tmc.user.dto.UserDto;
import com.tmc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

//    fsfsdfsdddddddddddddddd
    @GetMapping(value = "bm/badmin")
    public String home(){
        return "all";
    }

    @GetMapping(value = "ur/user")
    public String l1(){
        return "user";
    }

    @GetMapping(value = "pm/all")
    public String l2(){
        return "admin";
    }

    @GetMapping(value = "{id}")
    public Response<UserDto> getUserById(@PathVariable String id) {
        log.info("Getting User by id: {}, userController", id);
        return Response.<UserDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(userService.getUserById(id))
                .build();
    }

    @GetMapping()
    public Response<List<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "3") Integer pageSize) {
        log.info("Getting all users, userController");
        return Response.<List<UserDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(userService.getAllUsers(pageNumber, pageSize))
                .build();
    }

    @PostMapping(value = "/registration")
    public Response<String> createUser(@RequestBody UserDto userDto) {
        log.info("Create User with ID {}, userController", userDto.getUserId());
        return userService.createUser(userDto) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("User Successfully Added ")
                .build()
                : Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("User failed to add")
                .build();
    }
}
