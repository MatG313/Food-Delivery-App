package com.project.food.business.service.impl;

import com.project.food.api.dto.RestaurantDTO;
import com.project.food.domain.Restaurant;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.AddressRepository;
import com.project.food.infrastructure.database.repository.RestaurantRepository;
import com.project.food.infrastructure.database.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantServiceImplTest {

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private UserRepository userRepository;

    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    public void setUp() {
        restaurantService = new RestaurantServiceImpl(restaurantRepository, addressRepository, userRepository);
    }

    @Test
    public void testSaveRestaurant() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(/* provide necessary data */);
        AddressEntity addressEntity = new AddressEntity(/* provide necessary data */);
        UserEntity userEntity = new UserEntity(/* provide necessary data */);

        restaurantService.saveRestaurant(restaurantDTO, addressEntity, userEntity);

        verify(restaurantRepository).save(any(Restaurant.class), eq(addressEntity), eq(userEntity));
    }

    @Test
    public void testSaveAddress() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(/* provide necessary data */);

        restaurantService.saveAddress(restaurantDTO);

        verify(addressRepository).save(any(AddressEntity.class));
    }

    @Test
    public void testFindAll() {
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();

        when(restaurantRepository.findAll()).thenReturn(restaurantEntities);

        List<RestaurantEntity> result = restaurantService.findAll();

        assertSame(restaurantEntities, result);
    }

    @Test
    public void testFindById_RestaurantExists() {
        Long restaurantId = 1L;
        RestaurantEntity restaurantEntity = new RestaurantEntity(/* provide necessary data */);

        when(restaurantRepository.getRestaurantById(restaurantId)).thenReturn(restaurantEntity);

        RestaurantEntity result = restaurantService.findById(restaurantId);

        assertSame(restaurantEntity, result);
    }

    @Test
    public void testFindById_RestaurantNotFound() {
        Long restaurantId = 1L;

        when(restaurantRepository.getRestaurantById(restaurantId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> restaurantService.findById(restaurantId));
    }

    @Test
    public void testFindByName_RestaurantExists() {
        String restaurantName = "MyRestaurant";
        RestaurantEntity restaurantEntity = new RestaurantEntity(/* provide necessary data */);

        when(restaurantRepository.findByName(restaurantName)).thenReturn(restaurantEntity);

        RestaurantEntity result = restaurantService.findByName(restaurantName);

        assertSame(restaurantEntity, result);
    }

    @Test
    public void testFindByName_RestaurantNotFound() {
        String restaurantName = "NonExistentRestaurant";

        when(restaurantRepository.findByName(restaurantName)).thenReturn(null);

        RestaurantEntity result = restaurantService.findByName(restaurantName);

        assertNull(result);
    }

    @Test
    public void testGetRestaurantsByUserId() {
        Long userId = 1L;
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();

        when(restaurantRepository.findByOwnerId(userId)).thenReturn(restaurantEntities);

        List<RestaurantEntity> result = restaurantService.getRestaurantsByUserId(userId);

        assertSame(restaurantEntities, result);
    }

    @Test
    public void testAssignAddress_AddressExists() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(/* provide necessary data */);
        Integer addressId = 1;

        when(addressRepository.getExistedAddress(restaurantDTO)).thenReturn(addressId);
        AddressEntity addressEntity = new AddressEntity(/* provide necessary data */);
        when(addressRepository.getAddressById(addressId)).thenReturn(addressEntity);

        AddressEntity result = restaurantService.assignAddress(restaurantDTO);

        assertSame(addressEntity, result);
    }

    @Test
    public void testAssignAddress_AddressDoesNotExist() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(/* provide necessary data */);
        Integer addressId = null;

        when(addressRepository.getExistedAddress(restaurantDTO)).thenReturn(addressId);

        restaurantService.assignAddress(restaurantDTO);

        verify(addressRepository).save(any(AddressEntity.class));
    }

}