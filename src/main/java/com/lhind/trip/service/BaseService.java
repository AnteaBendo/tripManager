package com.lhind.trip.service;

import java.util.List;

public interface BaseService <CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> {
    RESPONSE_DTO save(CREATE_DTO createDto);
    RESPONSE_DTO save(CREATE_DTO createDto, Long dependencyId);
    void delete(Long id);
    RESPONSE_DTO findById(Long id);
    List<RESPONSE_DTO> findAll();
    RESPONSE_DTO update(UPDATE_DTO updateDto, Long id);
}
