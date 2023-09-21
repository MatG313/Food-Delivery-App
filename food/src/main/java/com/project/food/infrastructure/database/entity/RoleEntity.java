package com.project.food.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@EqualsAndHashCode(of = "roleId")
@ToString(of = {"roleId", "role"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role")
    public String role;
}
