package com.project.food.infrastructure.database.repository;

import com.project.food.domain.User;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;

    public void save(User user, AddressEntity address) {
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .addressId(address)
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
        userJpaRepository.save(userEntity);
    }

    public UserEntity findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }


    public List<UserEntity> findByRole(Integer role) {
        return userJpaRepository.findByRole(role);
    }

    public UserEntity getUserById(Integer userId) {
        return userJpaRepository.getReferenceById(userId);
    }
}
