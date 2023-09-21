package com.project.food.business.service.impl;

import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void testGetOrdersByRestaurant() {
        Long restaurantId = 1L;
        List<OrderEntity> orderEntities = new ArrayList<>();

        when(orderRepository.getOrdersByRestaurant(restaurantId)).thenReturn(orderEntities);

        List<OrderEntity> result = orderService.getOrdersByRestaurant(restaurantId);

        assertSame(orderEntities, result);
    }

    @Test
    public void testGetOrdersByUser() {
        Long userId = 1L;
        List<OrderEntity> orderEntities = new ArrayList<>();

        when(orderRepository.getOrdersByUser(userId)).thenReturn(orderEntities);

        List<OrderEntity> result = orderService.getOrdersByUser(userId);

        assertSame(orderEntities, result);
    }

    @Test
    public void testFindAll() {
        List<OrderEntity> orderEntities = new ArrayList<>();

        when(orderRepository.findAll()).thenReturn(orderEntities);

        List<OrderEntity> result = orderService.findAll();

        assertSame(orderEntities, result);
    }

    @Test
    public void testFindById() {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity(/* provide necessary data */);

        when(orderRepository.findById(orderId)).thenReturn(orderEntity);

        OrderEntity result = orderService.findById(orderId);

        assertSame(orderEntity, result);
    }

}