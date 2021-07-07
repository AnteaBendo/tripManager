package com.lhind.trip.mapper;

import com.lhind.trip.dto.country.CountryCreateDto;
import com.lhind.trip.dto.country.CountryResponseDto;
import com.lhind.trip.dto.country.CountryUpdateDto;
import com.lhind.trip.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper extends DtoMapper<CountryCreateDto, CountryUpdateDto, CountryResponseDto, Country>{
    public CountryMapper(){
        super(CountryResponseDto.class, Country.class);
    }
}
