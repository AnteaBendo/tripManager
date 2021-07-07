package com.lhind.trip.dto.user;

import com.lhind.trip.dto.trip.TripResponseDto;
import com.lhind.trip.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private List<TripResponseDto> trips;

}
