package com.lhind.trip.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdatePasswordDto {

    @NotBlank(message = "Password should not be empty")
    private String password;
}
