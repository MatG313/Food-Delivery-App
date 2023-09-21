package com.project.food.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "status", "orderNumber"})
public class Order {
    Long orderId;

    String status;

    Long restaurantId;

    Long userId;

    Long dishId;

    Integer orderNumber;
}
