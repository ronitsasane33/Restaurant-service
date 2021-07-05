package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
    Page<Restaurant> findAll(Pageable pageable);
    Optional<Restaurant> findByRestaurantNameIgnoreCase(String name);
}
