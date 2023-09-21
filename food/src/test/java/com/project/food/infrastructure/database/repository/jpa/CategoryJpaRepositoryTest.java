package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.CategoryEntity;
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
class CategoryJpaRepositoryTest {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Test
    public void testFindByName() {
        CategoryEntity category = new CategoryEntity();
        category.setName("TestCategory");
        categoryJpaRepository.save(category);

        Long expectedCategoryId = category.getCategoryId();

        Long categoryId = Long.valueOf(categoryJpaRepository.findByName("TestCategory").toString());

        assertEquals(expectedCategoryId, categoryId);
    }

}