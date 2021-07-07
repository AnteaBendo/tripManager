package com.lhind.trip.dto.airport;

import com.lhind.trip.model.City;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AirportUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private Long city;
}
