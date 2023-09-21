package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.DishEntity;
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
class DishJpaRepositoryTest {

    @Autowired
    private DishJpaRepository dishJpaRepository;

    @Test
    public void testFindByRestaurantId() {
        Long restaurantId = 1L;
        List<DishEntity> dishes = dishJpaRepository.findByRestaurantId(restaurantId);

        assertNotNull(dishes);
    }

}