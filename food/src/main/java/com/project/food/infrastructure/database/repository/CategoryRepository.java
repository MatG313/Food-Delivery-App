package com.project.food.infrastructure.database.repository;

import com.project.food.api.dto.DishDTO;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.repository.jpa.CategoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public void save(CategoryEntity category) {
        categoryJpaRepository.save(category);
    }

    public Integer getExistedCategory(DishDTO dishDTO) {
        return categoryJpaRepository.findByName(
                dishDTO.getCategoryName());
    }

    public CategoryEntity getCategoryById(Integer categoryId) {
        return categoryJpaRepository.getReferenceById(categoryId);
    }
}
