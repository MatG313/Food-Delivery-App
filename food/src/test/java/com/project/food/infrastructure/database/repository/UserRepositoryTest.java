package com.project.food.infrastructure.database.repository;

import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.jpa.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserRepositoryTest {

    @MockBean
    private UserJpaRepository userJpaRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository(userJpaRepository, addressRepository, roleRepository);
    }

    @Test
    public void testFindByEmail() {
        String userEmail = "user@example.com";
        UserEntity userEntity = new UserEntity();

        when(userJpaRepository.findByEmail(userEmail)).thenReturn(userEntity);

        UserEntity result = userRepository.findByEmail(userEmail);

        assertSame(userEntity, result);
    }

    @Test
    public void testFindAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();

        when(userJpaRepository.findAll()).thenReturn(userEntities);

        List<UserEntity> result = userRepository.findAll();

        assertSame(userEntities, result);
    }

    @Test
    public void testFindByRole() {
        Integer role = 1;
        List<UserEntity> userEntities = new ArrayList<>();

        when(userJpaRepository.findByRole(role)).thenReturn(userEntities);

        List<UserEntity> result = userRepository.findByRole(role);

        assertSame(userEntities, result);
    }

    @Test
    public void testGetUserById() {
        Integer userId = 1;
        UserEntity userEntity = new UserEntity();

        when(userJpaRepository.getReferenceById(userId)).thenReturn(userEntity);

        UserEntity result = userRepository.getUserById(userId);

        assertSame(userEntity, result);
    }

}