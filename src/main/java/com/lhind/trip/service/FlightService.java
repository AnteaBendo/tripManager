package com.lhind.trip.service;

import com.lhind.trip.dto.flight.FlightCreateDto;
import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.flight.FlightUpdateDto;
import com.lhind.trip.model.Flight;

import java.util.List;

public interface FlightService extends BaseService<FlightCreateDto, FlightUpdateDto, FlightResponseDto, Flight>{
    @Override
    FlightResponseDto save(FlightCreateDto flightCreateDto);

    @Override
    void delete(Long id);

    @Override
    FlightResponseDto findById(Long id);

    @Override
    List<FlightResponseDto> findAll();

    @Override
    FlightResponseDto save(FlightCreateDto flightCreateDto, Long dependencyId);

    FlightResponseDto update(FlightUpdateDto updateDto, Long id);
}
