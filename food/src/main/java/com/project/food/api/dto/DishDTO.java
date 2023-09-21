package com.project.food.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {

    Long dishId;

    String name;

    Integer categoryId;

    String categoryName;

    Long restaurantId;

    BigDecimal price;

    String description;

    String img;
}
