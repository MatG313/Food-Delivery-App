package com.project.food.business.service.impl;

import com.project.food.api.dto.DishDTO;
import com.project.food.domain.Dish;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.repository.CategoryRepository;
import com.project.food.infrastructure.database.repository.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DishServiceImplTest {

    @MockBean
    private DishRepository dishRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    private DishServiceImpl dishService;

    @BeforeEach
    public void setUp() {
        dishService = new DishServiceImpl(dishRepository, categoryRepository);
    }

    @Test
    public void testSaveDish() {
        DishDTO dishDTO = new DishDTO(/* provide necessary data */);
        CategoryEntity categoryEntity = new CategoryEntity(/* provide necessary data */);
        RestaurantEntity restaurantEntity = new RestaurantEntity(/* provide necessary data */);

        dishService.saveDish(dishDTO, categoryEntity, restaurantEntity);

        verify(dishRepository).save(any(Dish.class), eq(categoryEntity), eq(restaurantEntity));
    }

    @Test
    public void testSaveCategory() {
        DishDTO dishDTO = new DishDTO(/* provide necessary data */);

        dishService.saveCategory(dishDTO);

        verify(categoryRepository).save(any(CategoryEntity.class));
    }

    @Test
    public void testFindById_DishExists() {
        Long dishId = 1L;
        DishEntity dishEntity = new DishEntity(/* provide necessary data */);

        when(dishRepository.getDishById(dishId)).thenReturn(dishEntity);

        DishEntity result = dishService.findById(dishId);

        assertSame(dishEntity, result);
    }

    @Test
    public void testFindById_DishNotFound() {
        Long dishId = 1L;

        when(dishRepository.getDishById(dishId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> dishService.findById(dishId));
    }

    @Test
    public void testAssignCategory_CategoryExists() {
        DishDTO dishDTO = new DishDTO(/* provide necessary data */);
        Integer categoryId = 1;

        when(categoryRepository.getExistedCategory(dishDTO)).thenReturn(categoryId);
        CategoryEntity categoryEntity = new CategoryEntity(/* provide necessary data */);
        when(categoryRepository.getCategoryById(categoryId)).thenReturn(categoryEntity);

        CategoryEntity result = dishService.assignCategory(dishDTO);

        assertSame(categoryEntity, result);
    }

    @Test
    public void testAssignCategory_CategoryDoesNotExist() {
        DishDTO dishDTO = new DishDTO(/* provide necessary data */);
        Integer categoryId = null;

        when(categoryRepository.getExistedCategory(dishDTO)).thenReturn(categoryId);

        dishService.assignCategory(dishDTO);

        verify(categoryRepository).save(any(CategoryEntity.class));
    }

    @Test
    public void testGetDishesByRestaurant() {
        Long restaurantId = 1L;
        List<DishEntity> dishEntities = new ArrayList<>();

        when(dishRepository.getDishesByRestaurant(restaurantId)).thenReturn(dishEntities);

        List<DishEntity> result = dishService.getDishesByRestaurant(restaurantId);

        assertSame(dishEntities, result);
    }

    @Test
    public void testFindAll() {
        List<DishEntity> dishEntities = new ArrayList<>();

        when(dishRepository.findAll()).thenReturn(dishEntities);

        List<DishEntity> result = dishService.findAll();

        assertSame(dishEntities, result);
    }

}