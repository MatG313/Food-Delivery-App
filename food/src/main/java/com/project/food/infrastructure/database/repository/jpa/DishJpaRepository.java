package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishJpaRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT d FROM DishEntity d WHERE d.restaurantId.restaurantId = :restaurantId")
    List<DishEntity> findByRestaurantId(@Param("restaurantId") Long restaurantId);
}
