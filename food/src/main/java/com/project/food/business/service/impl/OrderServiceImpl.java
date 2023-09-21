package com.project.food.business.service.impl;

import com.project.food.api.dto.OrderDTO;
import com.project.food.business.service.OrderService;
import com.project.food.domain.Order;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public void saveOrder(OrderDTO orderDTO, UserEntity user, RestaurantEntity restaurant, DishEntity dish) {
        Order order = Order.builder()
                .orderId(orderDTO.getOrderId())
                .status(orderDTO.getStatus())
                .orderNumber(orderDTO.getOrderNumber())
                .userId(Long.valueOf(orderDTO.getUserId()))
                .restaurantId(orderDTO.getRestaurantId())
                .dishId(orderDTO.getDishId())
                .build();
        orderRepository.save(order, user, restaurant, dish);
    }

    @Override
    public void toggleStatus(Long id) {
        orderRepository.toggleStatus(id);
    }

    @Override
    public List<OrderEntity> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.getOrdersByRestaurant(restaurantId);
    }

    @Override
    public List<OrderEntity> getOrdersByUser(Long userId) {
        return orderRepository.getOrdersByUser(userId);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderEntity getById(Long id) {
        return orderRepository.findById(id);
    }
}
