package com.project.food.infrastructure.database.repository;

import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.repository.jpa.OrderJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderRepositoryTest {

    @MockBean
    private OrderJpaRepository orderJpaRepository;

    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository(orderJpaRepository);
    }

    @Test
    public void testGetOrdersByRestaurant() {
        Long restaurantId = 1L;
        List<OrderEntity> orderEntities = new ArrayList<>();
        when(orderJpaRepository.findByRestaurantId(restaurantId)).thenReturn(orderEntities);

        List<OrderEntity> result = orderRepository.getOrdersByRestaurant(restaurantId);

        assertSame(orderEntities, result);
    }

    @Test
    public void testGetOrdersByUser() {
        Long userId = 1L;
        List<OrderEntity> orderEntities = new ArrayList<>();
        when(orderJpaRepository.findByUserId(userId)).thenReturn(orderEntities);

        List<OrderEntity> result = orderRepository.getOrdersByUser(userId);

        assertSame(orderEntities, result);
    }

    @Test
    public void testFindAllOrders() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        when(orderJpaRepository.findAll()).thenReturn(orderEntities);

        List<OrderEntity> result = orderRepository.findAll();

        assertSame(orderEntities, result);
    }

    @Test
    public void testFindOrderById() {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        when(orderJpaRepository.getReferenceById(orderId)).thenReturn(orderEntity);

        OrderEntity result = orderRepository.findById(orderId);

        assertSame(orderEntity, result);
    }

}