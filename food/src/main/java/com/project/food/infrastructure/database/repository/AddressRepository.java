package com.project.food.infrastructure.database.repository;

import com.project.food.api.dto.RegisterDTO;
import com.project.food.api.dto.RestaurantDTO;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.repository.jpa.AddressJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    public Integer getExistedAddress(RegisterDTO addressDTO) {
        return addressJpaRepository.findByCityAndPostalCodeAndStreetAndCountry(
                addressDTO.getCity(),
                addressDTO.getStreet(),
                addressDTO.getCountry(),
                addressDTO.getPostalCode()
        );
    }

    public Integer getExistedAddress(RestaurantDTO addressDTO) {
        return addressJpaRepository.findByCityAndPostalCodeAndStreetAndCountry(
                addressDTO.getCity(),
                addressDTO.getStreet(),
                addressDTO.getCountry(),
                addressDTO.getPostalCode()
        );
    }

    public AddressEntity getAddressById(Integer address_id) {
        return addressJpaRepository.getReferenceById(address_id);
    }

    public void save(AddressEntity address) {
        addressJpaRepository.save(address);
    }

    public List<AddressEntity> getAllAddresses() {
        return addressJpaRepository.findAll();
    }
}
