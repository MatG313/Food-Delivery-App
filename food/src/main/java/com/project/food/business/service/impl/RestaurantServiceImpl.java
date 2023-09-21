package com.project.food.business.service.impl;

import com.project.food.api.dto.RestaurantDTO;
import com.project.food.business.service.RestaurantService;
import com.project.food.domain.Restaurant;
import com.project.food.domain.exception.NotFoundException;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.AddressRepository;
import com.project.food.infrastructure.database.repository.RestaurantRepository;
import com.project.food.infrastructure.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private AddressRepository addressRepository;

    private UserRepository userRepository;


    @Override
    public void saveRestaurant(RestaurantDTO restaurantDTO, AddressEntity addressEntity, UserEntity userEntity) {
        Restaurant restaurant = Restaurant.builder()
                .restaurantId(restaurantDTO.getRestaurantId())
                .name(restaurantDTO.getName())
                .address_id(restaurantDTO.getAddressId())
                .owner_id(restaurantDTO.getOwnerId())
                .range(restaurantDTO.getRange())
                .build();
        restaurantRepository.save(restaurant, addressEntity, userEntity);
    }

    @Override
    public void saveAddress(RestaurantDTO restaurantDTO) {
        AddressEntity address = AddressEntity.builder()
                .postalCode(restaurantDTO.getPostalCode())
                .street(restaurantDTO.getStreet())
                .city(restaurantDTO.getCity())
                .country(restaurantDTO.getCountry())
                .build();
        addressRepository.save(address);
    }

    @Override
    public List<RestaurantEntity> findAll() {
        return restaurantRepository.findAll();
    }

    private Restaurant mapToRestaurantDto(RestaurantEntity restaurant) {
        return Restaurant.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .build();
    }

    @Override
    public RestaurantEntity findById(Long restaurantId) {
        if (restaurantRepository.getRestaurantById(restaurantId).equals(null)) {
            throw new NotFoundException("Could not find restaurant by id: [%s]".formatted(restaurantId));
        }
        return restaurantRepository.getRestaurantById(restaurantId);
    }

    @Override
    public RestaurantEntity findByName(String name) {
        if (restaurantRepository.findByName(name) != null) {
            return restaurantRepository.findByName(name);
        }
        return null;
    }

    @Override
    public List<RestaurantEntity> getRestaurantsByUserId(Long userId) {
        return restaurantRepository.findByOwnerId(userId);
    }

    @Override
    public AddressEntity assignAddress(RestaurantDTO addressDTO) {
        Integer address_id = addressRepository.getExistedAddress(addressDTO);
        if (address_id != null) {
            return addressRepository.getAddressById(address_id);
        } else {
            saveAddress(addressDTO);
            Integer address_id2 = addressRepository.getExistedAddress(addressDTO);
            return addressRepository.getAddressById(address_id2);
        }
    }
}
