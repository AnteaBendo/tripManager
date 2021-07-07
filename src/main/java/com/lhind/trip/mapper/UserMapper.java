package com.lhind.trip.mapper;

import com.lhind.trip.dto.user.*;
import com.lhind.trip.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends DtoMapper<UserCreateDto, UserUpdateDto, UserResponseDto, User>{
    public UserMapper() {
        super(UserResponseDto.class, User.class);
    }

    public UserResponseWithoutTripsDto entityToUserResponseWithoutTripsDto(User entity){
        UserResponseWithoutTripsDto response = new UserResponseWithoutTripsDto();

        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setRole(entity.getRole());

        return response;
    }

    public User updateFromAdminToEntity(UserUpdateFromAdminDto update){
        User entity = new User();

        entity.setFirstName(update.getFirstName());
        entity.setLastName(update.getLastName());
        entity.setEmail(update.getEmail());
        entity.setRole(update.getRole());

        return entity;
    }
}
