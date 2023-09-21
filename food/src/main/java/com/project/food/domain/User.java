package com.project.food.domain;

import lombok.*;

@With
@Getter
@Setter
@Value
@Builder
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "name", "surname", "email", "password", "phone", "role"})
public class User {

    Long userId;
    String name;
    String surname;
    String email;
    String password;
    Integer addressId;
    String phone;
    Integer role;

}

