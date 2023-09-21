package com.project.food.infrastructure.database.repository;

import com.project.food.infrastructure.database.entity.RoleEntity;
import com.project.food.infrastructure.database.repository.jpa.RoleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleEntity findByRole(String role) {
        return roleJpaRepository.findByRole(role);
    }
}
