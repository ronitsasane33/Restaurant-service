package com.tmc.order.service.impl;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.mapper.OrderMapper;
import com.tmc.order.model.entity.Order;
import com.tmc.order.model.enums.OrderStatus;
import com.tmc.order.repository.OrderRepository;
import com.tmc.order.service.OrderService;
import com.tmc.restaurant.dto.FoodItemDto;
import com.tmc.restaurant.exception.OrderServiceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    public final OrderMapper orderMapper;
    public final OrderRepository orderRepository;

    public OrderServiceImpl(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getAllOrders(int pageNumber, int pageSize) {
        try {
            List<OrderDto> orders = orderMapper.
                    toOrderDTOs(orderRepository
                    .findAll(PageRequest.of(pageNumber, pageSize)).getContent());
            if (orders.size() > 0) {
                return orders;
            }
            throw new OrderServiceException("No orders placed");
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    public List<OrderDto> getOrderPage(int pagenumber) {
        try {
            List<OrderDto> orders = orderMapper
                    .toOrderDTOs(orderRepository
                            .findAll(PageRequest
                                    .of(pagenumber, 2)).getContent());
            if (orders.size() > 0) {
                return orders;
            }
            throw new OrderServiceException("No orders");
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    public OrderDto getOrderById(String id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (!order.isPresent()) {
                throw new OrderServiceException("Order with id" + id + "does not exist");
            }
            return orderMapper.toOderDTO(order.get());
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderDto> getAllOrdersByRestaurant(String restaurantId) {
        try {
            List<OrderDto> orders = orderMapper
                    .toOrderDTOs(orderRepository
                            .findAllByRestaurantRestaurantId(restaurantId));
            if (orders.size() > 0) {
                return orders;
            }
            throw new OrderServiceException("No orders placed at this restaurant");
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    public Boolean placeOrder(OrderDto orderDTO) {
        double orderTotal = calculateTotal(orderDTO.getFoodItems());
        double tax = orderTotal*0.2;
        orderDTO.getBilling().setTotal(orderTotal);
        orderDTO.getBilling().setTax(tax);
        Order order = orderMapper.ToOrder(orderDTO);
        order.setOrderStatus(OrderStatus.PLACED);
        orderRepository.save(order);
        return Boolean.TRUE;
    }


    public OrderDto cancelOrder(String id) {
        try {
            Optional<Order> ordersOptional = orderRepository.findById(id);
            if (!ordersOptional.isPresent()) {
                throw new OrderServiceException("Order with id" + id + "does not exist");
            } else {
                Order order = ordersOptional.get();
                order.setOrderStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
                return orderMapper.toOderDTO(order);
            }
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }


    public OrderDto updateOrder(String id, OrderDto orderDTO) {
        try {
            Optional<Order> ordersOptional = orderRepository.findById(id);
            if (!ordersOptional.isPresent()) {
                throw new OrderServiceException("Order with id" + id + "does not exist");
            } else {
                Order order = orderMapper.ToOrder(orderDTO);
                orderRepository.save(order);
                return orderMapper.toOderDTO(order);
            }
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
    }

    /**
     * Calculates the total
     * based on ordered food items
     * @param foodItems
     */

    public double calculateTotal(List<FoodItemDto> foodItems){
        double total = 0;
        for(FoodItemDto foodItem: foodItems){
            total += foodItem.getFoodItemPrice();
        }
        return total;
    }
}