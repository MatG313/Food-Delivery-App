package com.project.food.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "categoryId")
@ToString(of = {"categoryId", "name"})
public class Category {
    Long categoryId;
    String name;
}
