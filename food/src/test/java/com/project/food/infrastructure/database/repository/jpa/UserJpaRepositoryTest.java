package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.integration.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void testFindByEmail_NotFound() {
        UserEntity foundUser = userJpaRepository.findByEmail("nonexistent@example.com");

        assertNull(foundUser);
    }

    @Test
    public void testFindByRole_NotFound() {
        List<UserEntity> usersWithRole = userJpaRepository.findByRole(999);

        assertNotNull(usersWithRole);
        assertEquals(0, usersWithRole.size());
    }

    @Test
    public void testFindRestaurantsByUserId_NotFound() {
        List<RestaurantEntity> restaurantsByUserId = userJpaRepository.findRestaurantsByUserId(999L);

        assertNotNull(restaurantsByUserId);
        assertEquals(0, restaurantsByUserId.size());
    }

}