//package com.tmc.user.service.impl;
//
//import com.tmc.user.dto.UserDto;
//import com.tmc.user.entity.User;
//import com.tmc.user.repository.UserRespository;
//import com.tmc.user.service.UserService;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.Assert.*;
//import static org.mockito.AdditionalAnswers.returnsFirstArg;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceImplTest {
//    @Mock
//    private UserRespository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Test
//    public void getUserById() {
//        UserDto user = new UserDto("zaphod", "zaphod@mail.com", "asd",true,"asd");
//
//
//        doReturn(user.getUserId()).when(userRepository).findById("1");
//        UserDto us = userService.getUserById("1");
//        Assertions.assertEquals("zaphod" , us.getUserId());
////        Assertions.assertEquals(OrderStatus.PLACED, order.getOrderStatus());
//    }
//
//    @Test
//    public void getAllUsers() {
//    }
//
//    @Test
//    public void createUser() {
////        UserDto user = new UserDto("zaphod", "zaphod@mail.com", "asd",true,"asd");
////        Mockito.when(userRepository.save(any(User.class))).thenReturn();
////        boolean savedUser = userService.createUser(user);
////        assertTrue(savedUser);
//
//
//        //        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
////        assertEquals(24, businessImpl.findTheGreatestFromAllData());
//    }
//
//}