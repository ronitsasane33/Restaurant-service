package com.tmc.reservation.service.impl;

import com.tmc.reservation.dto.RestaurantTableDto;
import com.tmc.reservation.mapper.RestaurantTableMapper;
import com.tmc.reservation.model.RestaurantTable;
import com.tmc.reservation.model.enums.TableStatus;
import com.tmc.reservation.repository.RestaurantTableRepository;
import com.tmc.reservation.service.RestaurantTablesService;
import com.tmc.restaurant.exception.RestaurantServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<RestaurantTableDto> getAllTables() {
        log.info("Getting All Tables of all restaurants , Booking Service");
        List<RestaurantTableDto> tableDtos = restaurantTableMapper
                .toRestaurantTableDtos((List<RestaurantTable>) restaurantTableRepository.findAll());
        if (tableDtos.size() > 0) {
            return tableDtos;
        }
        throw new RestaurantServiceException("No Tables found");
    }

    @Override
    public List<RestaurantTableDto> getAllTablesByRestaurant(String id) {
        log.info("Getting all tabels by restaurant: {} , Booking Service", id);
         List<RestaurantTableDto> restaurantTableDtos = restaurantTableMapper
                .toRestaurantTableDtos(restaurantTableRepository.getAllByRestaurantRestaurantId(id));
        if (restaurantTableDtos.size() > 0) {
            return restaurantTableDtos;
        }
        throw new RestaurantServiceException("No Tables found for this restaurant");
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
