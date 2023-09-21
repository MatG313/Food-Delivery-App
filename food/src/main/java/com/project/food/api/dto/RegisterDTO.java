package com.project.food.api.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    Long userId;

    @NotEmpty
    String name;

    @NotEmpty
    String surname;

    @NotEmpty(message = "Email should not be empty")
    @Email
    String email;

    @NotEmpty(message = "Password should not be empty")
    String password;

    String passwordRepeat;

    private Boolean passwordsEqual;

    Integer addressId;

    String phone;

    Boolean role;

    Integer roleInteger;

    String postalCode;

    String street;

    String city;

    String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDTO that = (RegisterDTO) o;
        return Objects.equals(passwordsEqual, that.passwordsEqual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordsEqual);
    }

    @AssertTrue(message = "Passwords should match")
    public boolean isPasswordsEqual() {
        if (password != null) {
            return password.equals(passwordRepeat);
        }
        return true;
    }

}