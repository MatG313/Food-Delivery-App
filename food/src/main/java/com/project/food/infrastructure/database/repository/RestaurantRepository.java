package com.project.food.infrastructure.database.repository;

import com.project.food.domain.Restaurant;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;

    public List<RestaurantEntity> findAll() {
        return restaurantJpaRepository.findAll();
    }

    public RestaurantEntity getRestaurantById(Long restaurantId) {
        return restaurantJpaRepository.getReferenceById(restaurantId);
    }

    public List<AddressEntity> getAddressById(AddressEntity addressId) {
        return restaurantJpaRepository.findAddressByAddressId(addressId);
    }

    public RestaurantEntity findByName(String name) {
        return restaurantJpaRepository.findByName(name);
    }

    public List<RestaurantEntity> findByOwnerId(Long ownerId) {
        return restaurantJpaRepository.findByUserUserId(ownerId);
    }

    public void save(Restaurant restaurant, AddressEntity addressEntity, UserEntity userEntity) {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .addressId(addressEntity)
                .user(userEntity)
                .range(restaurant.getRange())
                .build();
        restaurantJpaRepository.save(restaurantEntity);
    }
}
