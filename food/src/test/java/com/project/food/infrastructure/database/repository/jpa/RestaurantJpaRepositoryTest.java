package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.RestaurantEntity;
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
class RestaurantJpaRepositoryTest {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    public void testFindByUserUserId() {
        Long userId = 1L;
        List<RestaurantEntity> restaurants = restaurantJpaRepository.findByUserUserId(userId);

        assertNotNull(restaurants);
    }

}