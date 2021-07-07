package com.lhind.trip.mapper;

import com.lhind.trip.dto.city.CityCreateDto;
import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.city.CityUpdateDto;
import com.lhind.trip.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends DtoMapper<CityCreateDto, CityUpdateDto, CityResponseDto, City>{
    public CityMapper(){
        super(CityResponseDto.class, City.class);
    }
}
