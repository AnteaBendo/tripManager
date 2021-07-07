package com.lhind.trip.security.entity;

import com.lhind.trip.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Role role;

}
