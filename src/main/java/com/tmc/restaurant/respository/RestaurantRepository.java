package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
}
