package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.airport.AirportCreateDto;
import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.airport.AirportUpdateDto;
import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.model.Airport;
import com.lhind.trip.repository.AirportRepository;
import com.lhind.trip.repository.CityRepository;
import com.lhind.trip.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl extends BaseServiceImpl<AirportCreateDto, AirportUpdateDto, AirportResponseDto, Airport> implements AirportService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AirportRepository airportRepository;

    public AirportServiceImpl(JpaRepository<Airport, Long> repository) {
        super(repository);
    }

    @Override
    public AirportResponseDto save(AirportCreateDto airportCreateDto) {
        Airport created = mapper.createDtoToEntity(airportCreateDto);
        created.setCity(cityRepository.getById(airportCreateDto.getCity()));
        return mapper.entityToResponseDto(repository.save(created));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public AirportResponseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<AirportResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public List<AirportResponseDto> findByCityId(Long id) {
        return airportRepository.findByCityId(id)
                .stream()
                .map(mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponseDto update(AirportUpdateDto airportUpdateDto, Long id) {
        Airport airport = repository.getById(id);

        airport.setName(airportUpdateDto.getName());
        airport.setCity(cityRepository.getById(airportUpdateDto.getCity()));

        return mapper.entityToResponseDto(repository.save(airport));
    }
}
