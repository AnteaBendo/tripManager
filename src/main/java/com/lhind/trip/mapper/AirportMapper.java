package com.lhind.trip.mapper;

import com.lhind.trip.dto.airport.AirportCreateDto;
import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.airport.AirportUpdateDto;
import com.lhind.trip.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper extends DtoMapper<AirportCreateDto, AirportUpdateDto, AirportResponseDto, Airport>{
    public AirportMapper(){
        super(AirportResponseDto.class, Airport.class);
    }
}
