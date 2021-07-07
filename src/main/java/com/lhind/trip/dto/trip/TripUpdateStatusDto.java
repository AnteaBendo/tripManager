package com.lhind.trip.dto.trip;

import com.lhind.trip.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TripUpdateStatusDto {
    @NotBlank(message = "Status date should not be empty")
    private Status status;

}
