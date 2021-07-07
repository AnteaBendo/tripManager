package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.trip.*;
import com.lhind.trip.enums.Status;
import com.lhind.trip.exception.UserException;
import com.lhind.trip.mapper.TripMapper;
import com.lhind.trip.model.City;
import com.lhind.trip.model.Trip;
import com.lhind.trip.model.User;
import com.lhind.trip.repository.CityRepository;
import com.lhind.trip.repository.FlightRepository;
import com.lhind.trip.repository.TripRepository;
import com.lhind.trip.repository.UserRepository;
import com.lhind.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl extends BaseServiceImpl<TripCreateDto, TripUpdateDto, TripResponseDto, Trip> implements TripService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripMapper tripMapper;

    public TripServiceImpl(JpaRepository<Trip, Long> repository) {
        super(repository);
    }

    @Override
    public TripResponseDto save(TripCreateDto createDto) {
        User user = userRepository.getByEmail(getPrincipalUserName()).orElseThrow(() -> new UserException("User not found"));
        Trip created = mapper.createDtoToEntity(createDto);
        created.setUser(user);
        created.setDepartureCity(cityRepository.getById(createDto.getDepartureCity()));
        created.setArrivalCity(cityRepository.getById(createDto.getArrivalCity()));
        created.setStatus(Status.CREATED);
        return mapper.entityToResponseDto(repository.save(created));
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public TripResponseDto findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TripResponseDto> findAll() {
        return super.findAll();
    }

    @Override
    public List<TripResponseDto> findByUser() {
        User user = userRepository.getByEmail(getPrincipalUserName()).orElseThrow(() -> new UserException("User not found"));
        return tripRepository.findByUser(user)
                .stream()
                .map(mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TripResponseDto updateStatus(TripUpdateStatusDto statusDto, Long id) {
        Trip trip = repository.getById(id);
        trip.setStatus(statusDto.getStatus());
        return mapper.entityToResponseDto(repository.save(trip));
    }

    @Override
    public TripResponseDto update(TripUpdateDto updateDto, Long id) {
        Trip trip = repository.getById(id);
        Trip update = mapper.updateDtoToEntity(updateDto);

        update.setDepartureCity(cityRepository.getById(updateDto.getDepartureCity()));
        update.setArrivalCity(cityRepository.getById(updateDto.getArrivalCity()));
        update.setId(trip.getId());
        update.setStatus(Status.CREATED);
        update.setUser(trip.getUser());
        update.setFlightsOfTrip(trip.getFlightsOfTrip());

        return mapper.entityToResponseDto(repository.save(update));
    }

    @Override
    public void changeStatusToWainting(Long id) {
        Trip trip = tripRepository.getById(id);
        trip.setStatus(Status.WAITING_FOR_APROVAL);
        repository.save(trip);
    }


    @Override
    public void changeStatusToApproved(Long id) {
        Trip trip = tripRepository.getById(id);
        trip.setStatus(Status.APPROVED);
        repository.save(trip);
    }

    @Override
    public List<TripResponseWithUserDto> findTripsWithStatusCREATED() {
        return tripRepository.findTripByStatus(Status.CREATED)
                .stream()
                .map(tripMapper::entityToResponseWithUserDto)
                .collect(Collectors.toList());
    }

    private String getPrincipalUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  ((UserDetails)principal).getUsername();
    }
}
