package com.project.food.business.service;

import com.project.food.api.dto.RegisterDTO;
import com.project.food.domain.User;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(RegisterDTO registerDTO, AddressEntity addressEntity);

    void saveAddress(RegisterDTO registerDTO);

    AddressEntity assignAddress(RegisterDTO addressDTO);

    UserEntity findUserByEmail(String email);

    UserEntity findUserById(Integer id);

    List<User> findAllUsers();
}
