package com.lhind.trip.service;

import com.lhind.trip.dto.user.*;
import com.lhind.trip.model.User;
import com.lhind.trip.security.entity.AuthenticationRequest;
import com.lhind.trip.security.entity.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends BaseService<UserCreateDto, UserUpdateDto, UserResponseDto, User>, UserDetailsService {
    void updatePassword(UserUpdatePasswordDto passwordDto);

    UserResponseDto updateFromAdmin(Long id, UserUpdateFromAdminDto role);

    @Override
    UserResponseDto save(UserCreateDto createDto);

    @Override
    void delete(Long id);

    UserResponseDto update(UserUpdateDto updateDto);

    @Override
    UserResponseDto findById(Long id);

    User findByEmail(String email);

    @Override
    List<UserResponseDto> findAll();

    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
