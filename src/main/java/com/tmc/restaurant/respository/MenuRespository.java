package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.Menu;
import com.tmc.restaurant.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRespository extends CrudRepository<Menu, String> {
    List<Menu> findAllByRestaurantRestaurantId(String id);
}
