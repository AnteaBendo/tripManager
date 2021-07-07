package com.lhind.trip.service;

import com.lhind.trip.dto.trip.*;
import com.lhind.trip.model.Trip;

import java.util.List;

public interface TripService extends BaseService<TripCreateDto, TripUpdateDto, TripResponseDto, Trip>{
    @Override
    TripResponseDto save(TripCreateDto tripCreateDto);

    @Override
    void delete(Long id);

    @Override
    TripResponseDto findById(Long id);

    @Override
    List<TripResponseDto> findAll();

    List<TripResponseDto> findByUser();

    @Override
    TripResponseDto update(TripUpdateDto updateDto, Long id);

    TripResponseDto updateStatus(TripUpdateStatusDto statusDto, Long id);

    void changeStatusToWainting(Long id);

    void changeStatusToApproved(Long id);

    List<TripResponseWithUserDto> findTripsWithStatusCREATED();

}
