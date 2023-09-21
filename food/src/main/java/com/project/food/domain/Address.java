package com.project.food.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "postalCode", "street", "city", "country"})
public class Address {

    Integer addressId;
    String postalCode;
    String street;
    String city;
    String country;
}
