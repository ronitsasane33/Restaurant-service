package com.tmc.restaurant.service.imp;

import com.tmc.restaurant.dto.RestaurantDto;
import com.tmc.restaurant.entity.Restaurant;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.mapper.RestaurantMapper;
import com.tmc.restaurant.respository.RestaurantRepository;
import com.tmc.restaurant.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDto getRestaurantById(String id) {
        log.info("Getting a restaurant by id: {} , restaurantService", id);
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if (!restaurant.isPresent()) {
                throw new RestaurantServiceException("Restaurant with id" + id + "does not exist");
            }
            return restaurantMapper.toRestaurantDto(restaurant.get());
        } catch (Exception e) {
            log.info("No restaurant present with the id {}", id);
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        try {
            log.info("Getting a restaurant by name: {} , restaurantService", name);
            Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(name);
            if (!restaurant.isPresent()) {
                throw new RestaurantServiceException("Restaurant with name" + name + "does not exist");
            }
            return restaurantMapper.toRestaurantDto(restaurant.get());
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    public List<RestaurantDto> getAllRestaurants(int pageNumber, int pageSize) {
        try {
            log.info("Getting all the restaurants, restaurantService");
            List<RestaurantDto> restaurantDtos = restaurantMapper
                    .toRestaurantDtos(restaurantRepository
                            .findAll(PageRequest.of(pageNumber, pageSize)).getContent());
            if (restaurantDtos.size() > 0) {
                return restaurantDtos;
            }
            throw new RestaurantServiceException("No Restaurants found");
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    public boolean createRestaurant(RestaurantDto restaurantDto) {
        log.info("Saving the restaurant: {} from RestaurantService", restaurantDto.getRestaurantName());
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return Boolean.TRUE;
    }

    @Override
    public RestaurantDto updateRestaurant(String id, RestaurantDto restaurantDto) {
        try {
            log.info("Updating the restaurant: {}, RestaurantService", restaurantDto.getRestaurantName());
            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
            if (!restaurantOptional.isPresent()) {
                throw new RestaurantServiceException("Restaurant with id" + id + "does not exist");
            } else {
                restaurantDto.setRestaurantId(id);
                Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDto);
                restaurantRepository.save(restaurant);
                return restaurantMapper.toRestaurantDto(restaurant);
            }
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }

    @Override
    public RestaurantDto deleteRestaurant(String id) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            log.warn("Deleting the restaurant: {} from RestaurantService", restaurant.get().getRestaurantName());
            if (!restaurant.isPresent()) {
                throw new RestaurantServiceException("Restaurant with id" + id + "does not exist");
            } else {
                restaurantRepository.delete(restaurant.get());
                return restaurantMapper.toRestaurantDto(restaurant.get());
            }
        } catch (Exception e) {
            throw new RestaurantServiceException(e.getMessage());
        }
    }
}
