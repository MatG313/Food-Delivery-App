package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.integration.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class OrderJpaRepositoryTest {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Test
    public void testFindByRestaurantId() {
        Long restaurantId = 1L;
        List<OrderEntity> orders = orderJpaRepository.findByRestaurantId(restaurantId);

        assertNotNull(orders);
    }

    @Test
    public void testFindByUserId() {
        Long userId = 1L;
        List<OrderEntity> orders = orderJpaRepository.findByUserId(userId);

        assertNotNull(orders);
    }

}