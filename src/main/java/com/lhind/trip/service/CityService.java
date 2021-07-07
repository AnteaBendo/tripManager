package com.lhind.trip.service;

import com.lhind.trip.dto.city.CityCreateDto;
import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.city.CityUpdateDto;
import com.lhind.trip.model.City;

import java.util.List;

public interface CityService extends BaseService<CityCreateDto, CityUpdateDto, CityResponseDto, City>{

    @Override
    CityResponseDto save(CityCreateDto cityCreateDto);

    @Override
    void delete(Long id);

    @Override
    CityResponseDto findById(Long id);

    @Override
    List<CityResponseDto> findAll();

    @Override
    CityResponseDto update(CityUpdateDto cityUpdateDto, Long id);

    List<CityResponseDto> findByCountryId(Long id);
}
