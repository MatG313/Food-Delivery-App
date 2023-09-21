package com.project.food.business.service;

import com.project.food.api.dto.DishDTO;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

public interface DishService {
    void saveDish(DishDTO dishDTO, CategoryEntity category, RestaurantEntity restaurant);

    void saveCategory(DishDTO dishDTO);

    DishEntity findById(Long dishId);

    CategoryEntity assignCategory(DishDTO dishDTO);

    List<DishEntity> getDishesByRestaurant(Long restaurantId);

    List<DishEntity> findAll();
}
