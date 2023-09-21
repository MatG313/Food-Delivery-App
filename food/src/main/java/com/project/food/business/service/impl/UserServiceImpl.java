package com.project.food.business.service.impl;

import com.project.food.api.dto.RegisterDTO;
import com.project.food.business.service.UserService;
import com.project.food.domain.User;
import com.project.food.infrastructure.database.entity.AddressEntity;
import com.project.food.infrastructure.database.entity.RoleEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import com.project.food.infrastructure.database.repository.AddressRepository;
import com.project.food.infrastructure.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegisterDTO userDTO, AddressEntity address) {
        if (userDTO.getRole().equals(true)) {
            userDTO.setRoleInteger(2);
        }
        if (userDTO.getRole().equals(false)) {
            userDTO.setRoleInteger(3);
        }
        User user = User.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .addressId(userDTO.getAddressId())
                .phone(userDTO.getPhone())
                .role(userDTO.getRoleInteger())
                .build();
        userRepository.save(user, address);
    }

    public List<UserEntity> getUsersByRole(Integer role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void saveAddress(RegisterDTO registerDTO) {
        AddressEntity address = AddressEntity.builder()
                .postalCode(registerDTO.getPostalCode())
                .street(registerDTO.getStreet())
                .city(registerDTO.getCity())
                .country(registerDTO.getCountry())
                .build();
        addressRepository.save(address);
    }

    @Override
    public AddressEntity assignAddress(RegisterDTO addressDTO) {
        Integer address_id = addressRepository.getExistedAddress(addressDTO);
        if (address_id != null) {
            return addressRepository.getAddressById(address_id);
        } else {
            saveAddress(addressDTO);
            Integer address_id2 = addressRepository.getExistedAddress(addressDTO);
            return addressRepository.getAddressById(address_id2);
        }
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map((UserEntity user) -> mapToUserDto(user, new RegisterDTO()))
                .collect(Collectors.toList());
    }

    private User mapToUserDto(UserEntity user, RegisterDTO registerDTO) {
        return User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .addressId(user.getAddressId().getAddressId())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        RoleEntity roleEntity = new RoleEntity();
        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        roleEntity.setRoleId(userEntity.getRole());
        roleEntity.setRole("ROLE_USER");
        if (userEntity.getRole() == 1) {
            roleEntity.setRole("ROLE_ADMIN");
        }
        if (userEntity.getRole() == 2) {
            roleEntity.setRole("ROLE_OWNER");
        }
        if (userEntity.getRole() == 3) {
            roleEntity.setRole("ROLE_USER");
        }
        Collection<RoleEntity> roles = List.of(roleEntity);
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), mapRolesToAuthorities(roles));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
