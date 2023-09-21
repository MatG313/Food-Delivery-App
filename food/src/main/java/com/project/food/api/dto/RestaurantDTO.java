package com.project.food.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    Long restaurantId;

    @NotEmpty
    String name;

    Integer addressId;

    String postalCode;

    String street;

    String city;

    String country;

    Integer ownerId;

    String range;
}
