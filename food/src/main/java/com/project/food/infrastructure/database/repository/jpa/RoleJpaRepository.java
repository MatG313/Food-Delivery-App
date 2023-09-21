package com.project.food.infrastructure.database.repository.jpa;

import com.project.food.infrastructure.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByRole (String role);
}
