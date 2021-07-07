package com.lhind.trip.service;

import com.lhind.trip.dto.airport.AirportCreateDto;
import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.airport.AirportUpdateDto;
import com.lhind.trip.model.Airport;

import java.util.List;

public interface AirportService extends BaseService<AirportCreateDto, AirportUpdateDto, AirportResponseDto, Airport>{
    @Override
    AirportResponseDto save(AirportCreateDto airportCreateDto);

    @Override
    void delete(Long id);

    @Override
    AirportResponseDto findById(Long id);

    @Override
    List<AirportResponseDto> findAll();

    @Override
    AirportResponseDto update(AirportUpdateDto airportUpdateDto, Long id);

    List<AirportResponseDto> findByCityId(Long id);
}
