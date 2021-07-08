package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRespository extends CrudRepository<FoodItem, String> {
    Page<FoodItem> findAll(Pageable pageable);
}
