package com.project.food.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    Long orderId;

    String status;

    Integer orderNumber;

    Long restaurantId;

    Integer userId;

    Long dishId;
}
