package com.project.food.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = "dishId")
@ToString(of = {"dishId", "name", "price", "description"})
public class Dish {
    Long dishId;
    String name;
    BigDecimal price;
    Integer categoryId;
    Long restaurantId;
    String description;
    String img;
}
