package com.lhind.trip.dto.city;

import com.lhind.trip.dto.country.CountryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityResponseDto {

    private Long id;
    private String name;
    private CountryResponseDto country;
}
