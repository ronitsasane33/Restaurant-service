package com.tmc.user.repository;

import com.tmc.restaurant.entity.Restaurant;
import com.tmc.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, String> {

    User findByUsername(String userName);
    Page<User> findAll(Pageable pageable);

}
