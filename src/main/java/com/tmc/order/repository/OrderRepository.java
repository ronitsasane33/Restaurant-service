package com.tmc.order.repository;

import com.tmc.order.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, String> {
    Page<Order> findAll(Pageable pageable);
}

