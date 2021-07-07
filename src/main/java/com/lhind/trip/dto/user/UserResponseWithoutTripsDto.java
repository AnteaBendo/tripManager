package com.lhind.trip.dto.user;

import com.lhind.trip.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseWithoutTripsDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
