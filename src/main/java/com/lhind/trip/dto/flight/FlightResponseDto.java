package com.lhind.trip.dto.flight;

import com.lhind.trip.dto.airport.AirportResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class FlightResponseDto {

    private Long id;
    private AirportResponseDto departureAirport;
    private AirportResponseDto landingAirport;
    private LocalDate departureDate;
    private LocalDate landingDate;
}
