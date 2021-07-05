package com.tmc.reservation.mapper;

import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.restaurant.mapper.RestaurantMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RestaurantMapper.class})
public interface RestaurantTableMapper {
    RestaurantTableDto toRestaurantTableDto(RestaurantTable restaurantTable);
    RestaurantTable toRestaurantTable(RestaurantTableDto restaurantTableDto);
    List<RestaurantTableDto> toRestaurantTableDtos(List<RestaurantTable> restaurantTables);
    List<RestaurantTable> toRestaurantTables(List<RestaurantTableDto> restaurantTableDtos);
}
