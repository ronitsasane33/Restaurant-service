package com.tmc.order.service;

import com.tmc.order.dto.OrderDto;
import com.tmc.order.mapper.OrderMapper;
import com.tmc.order.model.entity.Order;
import com.tmc.order.model.enums.OrderStatus;
import com.tmc.order.repository.OrderRepository;
import com.tmc.restaurant.exception.RestaurantServiceException;
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

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = orderMapper.toOrderDTOs((List<Order>) orderRepository.findAll());
        if (orders.size() > 0) {
            return orders;
        }
        throw new RestaurantServiceException("No orders placed");
    }

    public List<OrderDto> getOrderPage(int pagenumber) {
        List<OrderDto> orders = orderMapper.toOrderDTOs(orderRepository.findAll(PageRequest.of(pagenumber, 2)).getContent());
        if (orders.size() > 0) {
            return orders;
        }
        throw new RestaurantServiceException("No orders");
    }

    public OrderDto getOrderById(String eid) {
        Optional<Order> order = orderRepository.findById(eid);
        if (!order.isPresent()) {
            throw new RestaurantServiceException("Order with id" + eid + "does not exist");
        }
        return orderMapper.toOderDTO(order.get());
    }

    public Boolean placeOrder(OrderDto orderDTO) {
        Order order = orderMapper.ToOrder(orderDTO);
        order.setOrderStatus(OrderStatus.PLACED);
        orderRepository.save(order);
        return Boolean.TRUE;
    }


    public OrderDto cancelOrder(String id) {
        Optional<Order> ordersOptional = orderRepository.findById(id);
        if (!ordersOptional.isPresent()) {
            throw new RestaurantServiceException("Order with id" + id + "does not exist");
        } else {
            Order order = ordersOptional.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            return orderMapper.toOderDTO(order);
        }
    }

    public OrderDto updateOrder(String id, OrderDto orderDTO) {
        Optional<Order> ordersOptional = orderRepository.findById(id);
        if (!ordersOptional.isPresent()) {
            throw new RestaurantServiceException("Order with id" + id + "does not exist");
        } else {
            Order order = orderMapper.ToOrder(orderDTO);
            orderRepository.save(order);
            return orderMapper.toOderDTO(order);
        }
    }
}