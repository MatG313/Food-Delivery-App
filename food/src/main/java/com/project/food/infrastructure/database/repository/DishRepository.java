package com.project.food.infrastructure.database.repository;

import com.project.food.domain.Dish;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.repository.jpa.DishJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DishRepository {

    private final DishJpaRepository dishJpaRepository;

    public void save(Dish dish, CategoryEntity category, RestaurantEntity restaurant) {
        DishEntity dishEntity= DishEntity.builder()
                .dishId(dish.getDishId())
                .name(dish.getName())
                .price(dish.getPrice())
                .description(dish.getDescription())
                .img(dish.getImg())
                .category_id(category)
                .restaurantId(restaurant)
                .build();
        dishJpaRepository.save(dishEntity);
    }

    public DishEntity getDishById(Long dishId) {
        return dishJpaRepository.getReferenceById(dishId);
    }

    public List<DishEntity> getDishesByRestaurant(Long restaurantId) {
        return dishJpaRepository.findByRestaurantId(restaurantId);
    }

    public List<DishEntity> findAll() {
        return dishJpaRepository.findAll();
    }
}
