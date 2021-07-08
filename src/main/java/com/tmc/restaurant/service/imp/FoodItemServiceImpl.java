package com.tmc.restaurant.service.imp;

import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.entity.enums.FoodItemStatus;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.mapper.FoodItemMapper;
import com.tmc.restaurant.respository.FoodItemRespository;
import com.tmc.restaurant.service.FoodItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public FoodItemDto getFoodItemById(String id) {
        try {
            log.info("Getting Food Item by id: {} , FoodItemService", id);
            Optional<FoodItem> foodItem = foodItemRespository.findById(id);
            if (!foodItem.isPresent()) {
                throw new RestaurantServiceException("FoodItem with id" + id + "does not exist");
            }
            return foodItemMapper.toFoodItemDto(foodItem.get());
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public List<FoodItemDto> getAllFoodItems(int pageNumber, int pageSize) {
        try {
            log.info("Getting all Food Items , FoodItemService");
            List<FoodItemDto> foodItemDtos = foodItemMapper.
                    toFoodItemDtos(foodItemRespository
                            .findAll(PageRequest.of(pageNumber, pageSize)).getContent());
            if (foodItemDtos.size() > 0) {
                return foodItemDtos;
            }
            throw new RestaurantServiceException("No food Items found");
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public boolean createFoodItem(FoodItemDto foodItemDto) {
        log.info("Creating Food Item: {} , FoodItemService", foodItemDto);
        FoodItem foodItem = foodItemMapper.toFoodItem(foodItemDto);
        foodItemRespository.save(foodItem);
        return Boolean.TRUE;
    }

    @Override
    public FoodItemDto updateFoodItem(String foodItemId, FoodItemDto foodItemDto) {
        try{
            log.info("Updating Food Item: {}, FoodItemService", foodItemId);
            Optional<FoodItem> foodItemOptional = foodItemRespository.findById(foodItemId);
            if (!foodItemOptional.isPresent()) {
                throw new RestaurantServiceException("Food Item with id" + foodItemId + "does not exist");
            } else {
                FoodItem foodItem = foodItemOptional.get();
                if (foodItemDto.getFoodItemName() != null) foodItem.setFoodItemName(foodItemDto.getFoodItemName());
                if (foodItemDto.getFoodItemPrice() != 0) foodItem.setFoodItemPrice(foodItemDto.getFoodItemPrice());
                if (foodItemDto.getFoodItemDescription() != null) foodItem
                        .setFoodItemDescription(
                                foodItemDto.getFoodItemDescription());
                if (foodItemDto.getFoodItemStatus() != null) foodItem.setFoodItemStatus(foodItemDto.getFoodItemStatus());
                foodItemRespository.save(foodItem);
                return foodItemMapper.toFoodItemDto(foodItem);
            }
        }catch (Exception e){
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public FoodItemDto deleteFoodItem(String foodItemId) {
        try{
            Optional<FoodItem> foodItem = foodItemRespository.findById(foodItemId);
            log.warn("Deleting the Food Item: {}, FoodItemService", foodItemId);
            if (!foodItem.isPresent()) {
                throw new RestaurantServiceException("Food Item with id" + foodItemId + "does not exist");
            } else {
                foodItem.get().setFoodItemStatus(FoodItemStatus.DEACTIVE);
                foodItemRespository.save(foodItem.get());
                return foodItemMapper.toFoodItemDto(foodItem.get());
            }
        }catch (Exception e){
            throw new RestaurantServiceException(e.getMessage());
        }
    }
}
