package com.project.food.business.service;

import com.project.food.api.dto.RestaurantDTO;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface RestaurantService {
    void saveRestaurant(RestaurantDTO restaurantDTO, AddressEntity addressEntity, UserEntity userEntity);

    void saveAddress(RestaurantDTO restaurantDTO);

    List<RestaurantEntity> findAll();

    RestaurantEntity findById(Long restaurantId) throws FileNotFoundException;

    RestaurantEntity findByName(String name);

    List<RestaurantEntity> getRestaurantsByUserId(Long userId);

    AddressEntity assignAddress(RestaurantDTO addressDTO);
}
