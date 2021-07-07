package com.lhind.trip.dto.airport;

import com.lhind.trip.dto.city.CityResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportResponseDto {

    private Long id;
    private String name;
    private CityResponseDto city;
}
