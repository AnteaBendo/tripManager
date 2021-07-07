package com.lhind.trip.mapper;

import com.lhind.trip.dto.flight.FlightCreateDto;
import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.flight.FlightUpdateDto;
import com.lhind.trip.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper extends DtoMapper<FlightCreateDto, FlightUpdateDto, FlightResponseDto, Flight>{
    public FlightMapper() {
        super(FlightResponseDto.class, Flight.class);
    }
}
