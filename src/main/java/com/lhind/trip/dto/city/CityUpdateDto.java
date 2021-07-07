package com.lhind.trip.dto.city;

import com.lhind.trip.model.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CityUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private Long country;
}
