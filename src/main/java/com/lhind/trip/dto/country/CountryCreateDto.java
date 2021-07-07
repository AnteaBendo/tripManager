package com.lhind.trip.dto.country;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CountryCreateDto {

    @NotBlank
    private String name;
}
