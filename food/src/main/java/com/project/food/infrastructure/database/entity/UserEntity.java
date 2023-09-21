package com.project.food.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "name", "surname", "email", "password","restaurants", "phone", "role"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "address_id")
    private AddressEntity addressId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private List<RestaurantEntity> restaurants;

    public UserEntity(
            Long userId,
            String name,
            String surname,
            String email,
            String password,
            AddressEntity addressId,
            RestaurantEntity restaurant, // Change this parameter
            String phone,
            Integer role) {
        // Initialize other fields
        this.restaurants = new ArrayList<>(); // Initialize the list
        this.restaurants.add(restaurant); // Add the provided restaurant to the list
    }


    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private Integer role;

}
