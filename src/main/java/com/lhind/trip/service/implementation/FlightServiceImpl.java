package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.flight.FlightCreateDto;
import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.flight.FlightUpdateDto;
import com.lhind.trip.exception.FlightException;
import com.lhind.trip.model.Flight;
import com.lhind.trip.repository.AirportRepository;
import com.lhind.trip.repository.FlightRepository;
import com.lhind.trip.repository.TripRepository;
import com.lhind.trip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl extends BaseServiceImpl<FlightCreateDto, FlightUpdateDto, FlightResponseDto, Flight> implements FlightService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    public FlightServiceImpl(JpaRepository<Flight, Long> repository) {
        super(repository);
    }

    @Override
    public FlightResponseDto save(FlightCreateDto flightCreateDto, Long dependencyId) {
        System.out.println(flightCreateDto.getLandingDate() + " , " + flightCreateDto.getDepartureDate());
        if(flightCreateDto.getDepartureDate().isAfter(flightCreateDto.getLandingDate())){
            throw new FlightException("Please insert valid dates!!");
        }

        if(flightRepository.flightsInBetween(flightCreateDto.getDepartureDate(), flightCreateDto.getLandingDate(),
                flightCreateDto.getDepartureDate(), flightCreateDto.getLandingDate(), dependencyId).size() != 0){
            throw new FlightException("The flight is overlapping with existing ones!!");
        }

        Flight created = mapper.createDtoToEntity(flightCreateDto);
        created.setDepartureAirport(airportRepository.getById(flightCreateDto.getDepartureAirport()));
        created.setLandingAirport(airportRepository.getById(flightCreateDto.getLandingAirport()));
        created.setTrip(tripRepository.getById(dependencyId));

        return mapper.entityToResponseDto(repository.save(created));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public FlightResponseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<FlightResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public FlightResponseDto update(FlightUpdateDto updateDto, Long id) {
        Flight flight = repository.getById(id);
        flight.setDepartureDate(updateDto.getDepartureDate());
        flight.setDepartureAirport(airportRepository.getById(updateDto.getLandingAirport()));
        flight.setLandingDate(updateDto.getLandingDate());
        flight.setLandingAirport(airportRepository.getById(updateDto.getLandingAirport()));
        return mapper.entityToResponseDto(repository.save(flight));
    }
}
