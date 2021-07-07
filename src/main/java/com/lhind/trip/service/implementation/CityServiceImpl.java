package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.city.CityCreateDto;
import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.city.CityUpdateDto;
import com.lhind.trip.model.City;
import com.lhind.trip.repository.CityRepository;
import com.lhind.trip.repository.CountryRepository;
import com.lhind.trip.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl extends BaseServiceImpl<CityCreateDto, CityUpdateDto, CityResponseDto, City> implements CityService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    public CityServiceImpl(JpaRepository<City, Long> repository) {
        super(repository);
    }

    @Override
    public CityResponseDto save(CityCreateDto cityCreateDto) {
        City created = mapper.createDtoToEntity(cityCreateDto);
        created.setCountry(countryRepository.getById(cityCreateDto.getCountry()));
        return mapper.entityToResponseDto(repository.save(created));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public CityResponseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<CityResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public List<CityResponseDto> findByCountryId(Long id) {
        return cityRepository.findByCountryId(id)
                .stream()
                .map(mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityResponseDto update(CityUpdateDto cityUpdateDto, Long id) {
        City city = repository.getById(id);

        city.setName(cityUpdateDto.getName());
        city.setCountry(countryRepository.getById(cityUpdateDto.getCountry()));
        return mapper.entityToResponseDto(repository.save(city));
    }
}
