package com.project.food.infrastructure.database.repository;

import com.project.food.api.dto.DishDTO;
import com.project.food.infrastructure.database.entity.CategoryEntity;
import com.project.food.infrastructure.database.repository.jpa.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryRepositoryTest {

    @MockBean
    private CategoryJpaRepository categoryJpaRepository;

    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository = new CategoryRepository(categoryJpaRepository);
    }

    @Test
    public void testSaveCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryJpaRepository.save(categoryEntity)).thenReturn(categoryEntity);

        categoryRepository.save(categoryEntity);

        verify(categoryJpaRepository).save(categoryEntity);
    }

    @Test
    public void testGetExistedCategory() {
        DishDTO dishDTO = new DishDTO();

        when(categoryJpaRepository.findByName(dishDTO.getCategoryName())).thenReturn(1);

        Integer result = categoryRepository.getExistedCategory(dishDTO);

        assertEquals(1, result);
    }

    @Test
    public void testGetCategoryById() {
        CategoryEntity categoryEntity = new CategoryEntity();
        when(categoryJpaRepository.getReferenceById(1)).thenReturn(categoryEntity);

        CategoryEntity result = categoryRepository.getCategoryById(1);

        assertSame(categoryEntity, result);
    }

}