package com.project.food.infrastructure.database.repository;

import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantRepositoryTest {

    @MockBean
    private RestaurantJpaRepository restaurantJpaRepository;

    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        restaurantRepository = new RestaurantRepository(restaurantJpaRepository);
    }

    @Test
    public void testFindAllRestaurants() {
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        when(restaurantJpaRepository.findAll()).thenReturn(restaurantEntities);

        List<RestaurantEntity> result = restaurantRepository.findAll();

        assertSame(restaurantEntities, result);
    }

    @Test
    public void testGetRestaurantById() {
        Long restaurantId = 1L;
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        when(restaurantJpaRepository.getReferenceById(restaurantId)).thenReturn(restaurantEntity);

        RestaurantEntity result = restaurantRepository.getRestaurantById(restaurantId);

        assertSame(restaurantEntity, result);
    }

    @Test
    public void testFindRestaurantByName() {
        String restaurantName = "MyRestaurant";
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        when(restaurantJpaRepository.findByName(restaurantName)).thenReturn(restaurantEntity);

        RestaurantEntity result = restaurantRepository.findByName(restaurantName);

        assertSame(restaurantEntity, result);
    }

    @Test
    public void testFindRestaurantsByOwnerId() {
        Long ownerId = 1L;
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        when(restaurantJpaRepository.findByUserUserId(ownerId)).thenReturn(restaurantEntities);

        List<RestaurantEntity> result = restaurantRepository.findByOwnerId(ownerId);

        assertSame(restaurantEntities, result);
    }

}