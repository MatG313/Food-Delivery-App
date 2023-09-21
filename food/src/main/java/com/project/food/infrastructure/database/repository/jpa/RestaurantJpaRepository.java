package com.project.food.infrastructure.database.repository.jpa;


import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {

    RestaurantEntity findByName(String name);

    List<AddressEntity> findAddressByAddressId(AddressEntity addressId);

    List<RestaurantEntity> findByUserUserId(Long userId);
}
