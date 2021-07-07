package com.lhind.trip.dto.flight;

import com.lhind.trip.model.Airport;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class FlightUpdateDto {
    @NotBlank
    private Long departureAirport;

    @NotBlank
    private Long landingAirport;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate landingDate;
}
