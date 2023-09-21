package com.project.food.infrastructure.database.repository;

import com.project.food.api.dto.RegisterDTO;
import com.project.food.api.dto.RestaurantDTO;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.repository.jpa.AddressJpaRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
@AllArgsConstructor
class AddressRepositoryTest {

    @MockBean
    private AddressJpaRepository addressJpaRepository;

    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        addressRepository = new AddressRepository(addressJpaRepository);
    }

    @Test
    public void testGetExistedAddressWithRegisterDTO() {
        RegisterDTO registerDTO = new RegisterDTO();
        when(addressJpaRepository.findByCityAndPostalCodeAndStreetAndCountry(
                registerDTO.getCity(),
                registerDTO.getStreet(),
                registerDTO.getCountry(),
                registerDTO.getPostalCode()))
                .thenReturn(1);

        Integer result = addressRepository.getExistedAddress(registerDTO);

        assertEquals(1, result);
    }

    @Test
    public void testGetExistedAddressWithRestaurantDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        when(addressJpaRepository.findByCityAndPostalCodeAndStreetAndCountry(
                restaurantDTO.getCity(),
                restaurantDTO.getStreet(),
                restaurantDTO.getCountry(),
                restaurantDTO.getPostalCode()))
                .thenReturn(2);

        Integer result = addressRepository.getExistedAddress(restaurantDTO);

        assertEquals(2, result);
    }

    @Test
    public void testGetAddressById() {
        AddressEntity addressEntity = new AddressEntity();
        when(addressJpaRepository.getReferenceById(1)).thenReturn(addressEntity);

        AddressEntity result = addressRepository.getAddressById(1);

        assertSame(addressEntity, result);
    }

}