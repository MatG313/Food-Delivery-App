package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.RoleEntity;
import com.project.food.integration.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class RoleJpaRepositoryTest {

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Test
    public void testFindByRole() {
        RoleEntity testRole = new RoleEntity();
        testRole.setRole("ROLE_TEST");
        roleJpaRepository.save(testRole);

        RoleEntity foundRole = roleJpaRepository.findByRole("ROLE_TEST");

        assertNotNull(foundRole);
        assertEquals("ROLE_TEST", foundRole.getRole());
    }

    @Test
    public void testFindByRole_NotFound() {
        RoleEntity foundRole = roleJpaRepository.findByRole("NON_EXISTENT_ROLE");

        assertNull(foundRole);
    }

}