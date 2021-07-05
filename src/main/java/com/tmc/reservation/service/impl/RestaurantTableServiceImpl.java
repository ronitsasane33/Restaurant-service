package com.tmc.reservation.service.impl;

import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.mapper.RestaurantTableMapper;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.reservation.model.enums.TableStatus;
import com.tmc.reservation.repository.RestaurantTableRepository;
import com.tmc.reservation.service.RestaurantTablesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RestaurantTableServiceImpl implements RestaurantTablesService {

    private final RestaurantTableMapper restaurantTableMapper;
    private final RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableServiceImpl(RestaurantTableMapper restaurantTableMapper, RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableMapper = restaurantTableMapper;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @Override
    public boolean createRestaurantTable(RestaurantTableDto restaurantTableDto) {
        RestaurantTable restaurantTable = restaurantTableMapper.toRestaurantTable(restaurantTableDto);
        restaurantTableRepository.save(restaurantTable);
        return Boolean.TRUE;
    }

    @Override
    public boolean createBulkTables(int numberOfTables) {
        for(int tableNumber = 1; tableNumber<=numberOfTables;tableNumber++) {
            RestaurantTable restaurantTable = new RestaurantTable();
            restaurantTable.setTableNumber(tableNumber);
            restaurantTable.setTableStatus(TableStatus.AVAILABLE);
            restaurantTable.setCapacity(6);
            restaurantTableRepository.save(restaurantTable);
        }
        return Boolean.TRUE;
    }
}
