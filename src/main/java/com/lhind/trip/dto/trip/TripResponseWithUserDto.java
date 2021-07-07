package com.lhind.trip.dto.trip;

import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.user.UserResponseWithoutTripsDto;
import com.lhind.trip.enums.Reason;
import com.lhind.trip.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TripResponseWithUserDto {
    private Long id;
    private Reason reason;
    private String description;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Status status;
    private CityResponseDto departureCity;
    private CityResponseDto arrivalCity;
    private UserResponseWithoutTripsDto user;
}
