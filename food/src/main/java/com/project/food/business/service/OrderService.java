package com.project.food.business.service;

import com.project.food.api.dto.OrderDTO;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;

import java.util.List;

public interface OrderService {

    void saveOrder(OrderDTO orderDTO, UserEntity user, RestaurantEntity restaurant, DishEntity dish);

    void toggleStatus(Long id);

    List<OrderEntity> getOrdersByRestaurant(Long restaurantId);

    List<OrderEntity> getOrdersByUser(Long userId);

    List<OrderEntity> findAll();

    OrderEntity findById(Long id);

    OrderEntity getById(Long id);
}
