package com.tmc.restaurant.mapper;

import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.Restaurant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {AddressMapper.class})
public interface RestaurantMapper {
    RestaurantDto toRestaurantDto(Restaurant restaurant);
    Restaurant toRestaurant(RestaurantDto restaurantDto);
    List<Restaurant> toRestaurants(List<RestaurantDto> restaurantDtos);
    List<RestaurantDto> toRestaurantDtos(List<Restaurant> restaurants);
}
