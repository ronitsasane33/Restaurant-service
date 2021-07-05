package com.tmc.restaurant.service.imp;


import com.tmc.restaurant.respository.FoodItemRespository;
import com.tmc.restaurant.service.FoodItemService;
import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.mapper.FoodItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRespository foodItemRespository;
    private final FoodItemMapper foodItemMapper;

    public FoodItemServiceImpl(FoodItemRespository foodItemRespository, FoodItemMapper foodItemMapper) {
        this.foodItemRespository = foodItemRespository;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    public List<FoodItemDto> getAllFoodItems() {
        List<FoodItemDto> foodItemDtos = foodItemMapper.toFoodItemDtos((List<FoodItem>) foodItemRespository.findAll());
        if (foodItemDtos.size() > 0) {
            return foodItemDtos;
        }
        throw new RestaurantServiceException("No food Items found");
    }

    @Override
    public boolean createFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = foodItemMapper.toFoodItem(foodItemDto);
        foodItemRespository.save(foodItem);
        return Boolean.TRUE;
    }
}
