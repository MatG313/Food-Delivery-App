package com.project.food.domain;

import lombok.*;

@With
@Getter
@Setter
@Value
@Builder
@EqualsAndHashCode(of = "roleId")
@ToString(of = {"roleId", "name"})
public class Role {
    Integer roleId;

    String name;
}
