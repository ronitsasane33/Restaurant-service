package com.tmc.restaurant.respository;

import com.tmc.restaurant.entity.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRespository extends CrudRepository<Menu, String> {
}
