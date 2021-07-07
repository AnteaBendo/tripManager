package com.lhind.trip.service;

import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.country.CountryCreateDto;
import com.lhind.trip.dto.country.CountryResponseDto;
import com.lhind.trip.dto.country.CountryUpdateDto;
import com.lhind.trip.model.Country;

import java.util.List;

public interface CountryService extends BaseService<CountryCreateDto, CountryUpdateDto, CountryResponseDto, Country>{
    @Override
    CountryResponseDto save(CountryCreateDto countryCreateDto);

    @Override
    void delete(Long id);

    @Override
    CountryResponseDto findById(Long id);

    @Override
    List<CountryResponseDto> findAll();

    @Override
    CountryResponseDto update(CountryUpdateDto updateDto, Long id);

}
