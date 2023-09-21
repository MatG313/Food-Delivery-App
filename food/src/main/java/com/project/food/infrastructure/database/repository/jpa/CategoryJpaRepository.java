package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT categoryId FROM CategoryEntity WHERE name= :name")
    Integer findByName(@Param("name")String name);
}
