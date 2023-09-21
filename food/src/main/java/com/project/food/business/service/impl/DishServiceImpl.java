package com.project.food.business.service.impl;

import com.project.food.api.dto.DishDTO;
import com.project.food.business.service.DishService;
import com.project.food.domain.Dish;
import com.project.food.domain.exception.NotFoundException;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.repository.CategoryRepository;
import com.project.food.infrastructure.database.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private CategoryRepository categoryRepository;

    @Override
    public void saveDish(DishDTO dishDTO, CategoryEntity category, RestaurantEntity restaurant) {
        Dish dish = Dish.builder()
                .dishId(dishDTO.getDishId())
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .categoryId(dishDTO.getCategoryId())
                .description(dishDTO.getDescription())
                .restaurantId(dishDTO.getRestaurantId())
                .img(dishDTO.getImg() )
                .build();
        dishRepository.save(dish, category, restaurant);
    }

    @Override
    public void saveCategory(DishDTO dishDTO) {
        CategoryEntity category = CategoryEntity.builder()
                .name(dishDTO.getCategoryName())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public DishEntity findById(Long dishId) {
        if (dishRepository.getDishById(dishId).equals(null)) {
            throw new NotFoundException("Could not find dish by id: [%s]".formatted(dishId));
        }
        return dishRepository.getDishById(dishId);
    }

    @Override
    public CategoryEntity assignCategory(DishDTO dishDTO) {
        Integer category_id = categoryRepository.getExistedCategory(dishDTO);
        if (category_id != null) {
            return categoryRepository.getCategoryById(category_id);
        } else {
            saveCategory(dishDTO);
            Integer category_id2 = categoryRepository.getExistedCategory(dishDTO);
            return categoryRepository.getCategoryById(category_id2);
        }
    }

    @Override
    public List<DishEntity> getDishesByRestaurant(Long restaurantId){
        return dishRepository.getDishesByRestaurant(restaurantId);
    }

    @Override
    public List<DishEntity> findAll() {
        return dishRepository.findAll();
    }
}
