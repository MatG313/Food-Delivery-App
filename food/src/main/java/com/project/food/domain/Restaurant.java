package com.project.food.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name", "range"})
public class Restaurant {
    Long restaurantId;
    String name;
    Integer address_id;
    Integer owner_id;
    String range;
}
