package com.project.food.infrastructure.database.repository;

import com.project.food.infrastructure.database.entity.RoleEntity;
import com.project.food.infrastructure.database.repository.jpa.RoleJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoleRepositoryTest {

    @MockBean
    private RoleJpaRepository roleJpaRepository;

    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        roleRepository = new RoleRepository(roleJpaRepository);
    }

    @Test
    public void testFindRoleByName() {
        String roleName = "ROLE_ADMIN";
        RoleEntity roleEntity = new RoleEntity();

        // Stub the findByRole method of the repository
        when(roleJpaRepository.findByRole(roleName)).thenReturn(roleEntity);

        RoleEntity result = roleRepository.findByRole(roleName);

        assertSame(roleEntity, result);
    }

}