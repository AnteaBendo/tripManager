package com.lhind.trip.service.implementation;

import com.lhind.trip.mapper.DtoMapper;
import com.lhind.trip.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BaseServiceImpl<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> implements BaseService<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> {

    protected final JpaRepository<ENTITY, Long> repository;

    @Autowired
    protected DtoMapper<CREATE_DTO, UPDATE_DTO, RESPONSE_DTO, ENTITY> mapper;

    public BaseServiceImpl(JpaRepository<ENTITY, Long> repository) {
        this.repository = repository;
    }

    @Override
    public RESPONSE_DTO save(CREATE_DTO createDto) {
        return mapper.entityToResponseDto(repository.save(mapper.createDtoToEntity(createDto)));
    }

    @Override
    public RESPONSE_DTO save(CREATE_DTO createDto, Long dependencyId) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new RuntimeException("Entity with id: " + id + " does not exist");
                });
    }

    @Override
    public RESPONSE_DTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToResponseDto)
                .orElseThrow(() -> {
                    throw new RuntimeException("Entity with id: " + id + " does not exist");
                });
    }

    @Override
    public List<RESPONSE_DTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RESPONSE_DTO update(UPDATE_DTO updateDto, Long id) {
        return null;
    }
}
