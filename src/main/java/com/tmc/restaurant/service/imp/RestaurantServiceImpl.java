package com.tmc.restaurant.service.imp;

import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.Restaurant;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.respository.RestaurantRepository;
import com.tmc.restaurant.mapper.RestaurantMapper;
import com.tmc.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDto getRestaurantById(String id) {
        return restaurantMapper.toRestaurantDto(restaurantRepository.findById(id).get());
    }

    public List<RestaurantDto> getAllRestaurants(){
        List<RestaurantDto> restaurantDtos  = restaurantMapper.toRestaurantDtos((List<Restaurant>) restaurantRepository.findAll());
        if(restaurantDtos.size()>0){
            return restaurantDtos;
        }
        throw new RestaurantServiceException("No Restaurants foung");
    }

    public boolean createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return Boolean.TRUE;
    }
}
