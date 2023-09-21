package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT d FROM OrderEntity d WHERE d.restaurantId.restaurantId = :restaurantId")
    List<OrderEntity> findByRestaurantId(@Param("restaurantId") Long restaurantId);
    @Query("SELECT d FROM OrderEntity d WHERE d.userId.userId = :userId")
    List<OrderEntity> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity SET status= :status WHERE orderId = :id")
    void toggleStatus(@Param("id") Long id,@Param("status") String status);
}
