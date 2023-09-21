package com.project.food.business.service.impl;

import com.project.food.api.dto.RegisterDTO;
import com.project.food.domain.User;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.AddressRepository;
import com.project.food.infrastructure.database.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, addressRepository, passwordEncoder);
    }

    @Test
    public void testGetUsersByRole() {
        Integer role = 1;
        List<UserEntity> userEntities = new ArrayList<>();

        when(userRepository.findByRole(role)).thenReturn(userEntities);

        List<UserEntity> result = userService.getUsersByRole(role);

        assertSame(userEntities, result);
    }

    @Test
    public void testSaveAddress() {
        RegisterDTO registerDTO = new RegisterDTO(/* provide necessary data */);

        userService.saveAddress(registerDTO);

        verify(addressRepository).save(any(AddressEntity.class));
    }

    @Test
    public void testAssignAddress_AddressExists() {
        RegisterDTO addressDTO = new RegisterDTO(/* provide necessary data */);
        Integer addressId = 1;

        when(addressRepository.getExistedAddress(addressDTO)).thenReturn(addressId);
        AddressEntity addressEntity = new AddressEntity(/* provide necessary data */);
        when(addressRepository.getAddressById(addressId)).thenReturn(addressEntity);

        AddressEntity result = userService.assignAddress(addressDTO);

        assertSame(addressEntity, result);
    }

    @Test
    public void testAssignAddress_AddressDoesNotExist() {
        RegisterDTO addressDTO = new RegisterDTO(/* provide necessary data */);
        Integer addressId = null;

        when(addressRepository.getExistedAddress(addressDTO)).thenReturn(addressId);

        userService.assignAddress(addressDTO);

        verify(addressRepository).save(any(AddressEntity.class));
    }

    @Test
    public void testFindUserByEmail_UserExists() {
        String userEmail = "user@example.com";
        UserEntity userEntity = new UserEntity(/* provide necessary data */);

        when(userRepository.findByEmail(userEmail)).thenReturn(userEntity);

        UserEntity result = userService.findUserByEmail(userEmail);

        assertSame(userEntity, result);
    }

    @Test
    public void testFindUserById() {
        Integer userId = 1;
        UserEntity userEntity = new UserEntity(/* provide necessary data */);

        when(userRepository.getUserById(userId)).thenReturn(userEntity);

        UserEntity result = userService.findUserById(userId);

        assertSame(userEntity, result);
    }

    @Test
    public void testFindAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(userEntities);

        List<User> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(userEntities.size(), result.size());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistent@example.com";

        when(userRepository.findByEmail(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }

}