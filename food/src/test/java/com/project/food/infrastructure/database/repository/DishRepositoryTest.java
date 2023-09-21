package com.project.food.infrastructure.database.repository;

import com.project.food.domain.Dish;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.repository.jpa.DishJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class DishRepositoryTest {

    @MockBean
    private DishJpaRepository dishJpaRepository;

    private DishRepository dishRepository;

    private Dish dish;

    @BeforeEach
    public void setUp() {
        dishRepository = new DishRepository(dishJpaRepository);
    }

    @Test
    public void testGetDishById() {
        Long dishId = 1L;
        DishEntity dishEntity = new DishEntity();
        when(dishJpaRepository.getReferenceById(dishId)).thenReturn(dishEntity);

        DishEntity result = dishRepository.getDishById(dishId);

        assertSame(dishEntity, result);
    }

    @Test
    public void testGetDishesByRestaurant() {
        Long restaurantId = 1L;
        List<DishEntity> dishEntities = new ArrayList<>();
        when(dishJpaRepository.findByRestaurantId(restaurantId)).thenReturn(dishEntities);

        List<DishEntity> result = dishRepository.getDishesByRestaurant(restaurantId);

        assertSame(dishEntities, result);
    }

    @Test
    public void testFindAllDishes() {
        List<DishEntity> dishEntities = new ArrayList<>();
        when(dishJpaRepository.findAll()).thenReturn(dishEntities);

        List<DishEntity> result = dishRepository.findAll();

        assertSame(dishEntities, result);
    }

}