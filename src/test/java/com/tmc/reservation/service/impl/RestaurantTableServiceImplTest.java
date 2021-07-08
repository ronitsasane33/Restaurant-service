//package com.tmc.reservation.service.impl;
//
//import com.tmc.reservation.dto.RestaurantTableDto;
//import com.tmc.reservation.mapper.RestaurantTableMapper;
//import com.tmc.reservation.model.RestaurantTable;
//import com.tmc.reservation.model.enums.TableStatus;
//import com.tmc.reservation.repository.RestaurantTableRepository;
//import com.tmc.reservation.service.RestaurantTablesService;
//import com.tmc.restaurant.dto.AddressDto;
//import com.tmc.restaurant.dto.RestaurantDto;
//import com.tmc.restaurant.entity.Address;
//import com.tmc.restaurant.entity.Restaurant;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//
//public class RestaurantTableServiceImplTest {
//
//    @Mock
//    private RestaurantTableRepository restaurantTableRepository;
//    private RestaurantTableMapper restaurantTableMapper;
//
//
//    @InjectMocks
//    RestaurantTablesService restaurantTablesService = new RestaurantTableServiceImpl(restaurantTableMapper,
//            restaurantTableRepository);
//
//
//    @Test
//    public void getAllTables() {
//
//    }
//
//    @Test
//    public void getAllTablesByRestaurant() {
//    }
//
//    @Test
//    public void createRestaurantTable() {
//        Mockito.when(restaurantTableRepository.save(createRestaurantTableDto()))
//                .thenReturn(new RestaurantTable());
//    }
//
//    @Test
//    public void createBulkTables() {
//    }
//
//    RestaurantTable createRestaurantTableDto(){
//        RestaurantTable restaurantTable = new RestaurantTable();
//        restaurantTable.setTableId("1");
//        restaurantTable.setTableNumber(23);
//        restaurantTable.setTableStatus(TableStatus.AVAILABLE);
//        restaurantTable.setCapacity(25);
//        Restaurant restaurantDto = new Restaurant();
//        restaurantDto.setRestaurantId("1");
//        restaurantDto.setRestaurantName("MacD");
//        Address addressDto = new Address();
//        addressDto.setAddressId("2");
//        addressDto.setAddressLine1("one Lane");
//        addressDto.setAddressLine2("teo lane");
//        addressDto.setCity("New York");
//        addressDto.setState("NY");
//        addressDto.setZip("09231");
//        restaurantDto.setAddress(addressDto);
//        restaurantTable.setRestaurant(restaurantDto);
//        return  restaurantTable;
//    }
//}