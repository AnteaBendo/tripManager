package com.lhind.trip.mapper;

import com.lhind.trip.dto.trip.TripCreateDto;
import com.lhind.trip.dto.trip.TripResponseDto;
import com.lhind.trip.dto.trip.TripResponseWithUserDto;
import com.lhind.trip.dto.trip.TripUpdateDto;
import com.lhind.trip.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripMapper extends DtoMapper<TripCreateDto, TripUpdateDto, TripResponseDto, Trip>{
    public TripMapper() {
        super(TripResponseDto.class, Trip.class);
    }

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private UserMapper userMapper;

    public TripResponseWithUserDto entityToResponseWithUserDto(Trip entity){
        TripResponseWithUserDto response = new TripResponseWithUserDto();

        response.setId(entity.getId());
        response.setReason(entity.getReason());
        response.setDescription(entity.getDescription());
        response.setDepartureCity(cityMapper.entityToResponseDto(entity.getDepartureCity()));
        response.setArrivalCity(cityMapper.entityToResponseDto(entity.getArrivalCity()));
        response.setDepartureDate(entity.getDepartureDate());
        response.setArrivalDate(entity.getArrivalDate());
        response.setUser(userMapper.entityToUserResponseWithoutTripsDto(entity.getUser()));

        return response;
    }
}
