package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
    List<UserEntity> findByRole(Integer role);
    @Query("SELECT u.restaurants FROM UserEntity u WHERE u.userId = :userId")
    List<RestaurantEntity> findRestaurantsByUserId(@Param("userId") Long userId);
}
