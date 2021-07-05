package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.FoodItem;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRespository extends CrudRepository<FoodItem, String> {
}
