package com.project.food.business.service;

import com.project.food.api.dto.DishDTO;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface DishService {
    void saveDish(DishDTO dishDTO, CategoryEntity category, RestaurantEntity restaurant);

    void saveCategory(DishDTO dishDTO);

    DishEntity findById(Long dishId) throws FileNotFoundException;

    CategoryEntity assignCategory(DishDTO dishDTO);

    List<DishEntity> getDishesByRestaurant(Long restaurantId);

    List<DishEntity> findAll();
}
