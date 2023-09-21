package com.project.food.infrastructure.database.repository;

import com.project.food.domain.Order;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.jpa.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public void save(Order order, UserEntity user, RestaurantEntity restaurant, DishEntity dish) {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .orderNumber(order.getOrderNumber())
                .userId(user)
                .restaurantId(restaurant)
                .dishId(dish.getDishId())
                .build();
        orderJpaRepository.save(orderEntity);
    }

    public void toggleStatus(Long id) {
        String status = "DONE";
        orderJpaRepository.toggleStatus(id, status);
    }

    public List<OrderEntity> getOrdersByRestaurant(Long restaurantId) {
        return orderJpaRepository.findByRestaurantId(restaurantId);
    }

    public List<OrderEntity> getOrdersByUser(Long userId) {
        return orderJpaRepository.findByUserId(userId);
    }

    public List<OrderEntity> findAll() {
        return orderJpaRepository.findAll();
    }


    public OrderEntity findById(Long id) {
        return orderJpaRepository.getReferenceById(id);
    }
}
