package com.lhind.trip.dto.user;

import com.lhind.trip.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateDto {

    @NotBlank(message = "First name should not be empty")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    private String lastName;

    @NotBlank(message = "Email should not be empty")
    private String email;

    @NotBlank(message = "Role should not be empty")
    private Role role;

    @NotBlank(message = "Password should not be empty")
    private String password;
}
