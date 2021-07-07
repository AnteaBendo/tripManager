package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.country.CountryCreateDto;
import com.lhind.trip.dto.country.CountryResponseDto;
import com.lhind.trip.dto.country.CountryUpdateDto;
import com.lhind.trip.model.Country;
import com.lhind.trip.service.CountryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl extends BaseServiceImpl<CountryCreateDto, CountryUpdateDto, CountryResponseDto, Country> implements CountryService {
    public CountryServiceImpl(JpaRepository<Country, Long> repository) {
        super(repository);
    }

    @Override
    public CountryResponseDto save(CountryCreateDto countryCreateDto) {
        return super.save(countryCreateDto);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public CountryResponseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<CountryResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public CountryResponseDto update(CountryUpdateDto updateDto, Long id) {
        Country country = repository.getById(id);
        Country update = mapper.updateDtoToEntity(updateDto);

        update.setId(id);
        update.setCities(country.getCities());

        return mapper.entityToResponseDto(repository.save(update));
    }
}
