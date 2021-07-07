package com.lhind.trip.dto.trip;

import com.lhind.trip.enums.Reason;
import com.lhind.trip.enums.Status;
import com.lhind.trip.model.City;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TripCreateDto {

    @NotBlank(message = "Reason should not be empty")
    private Reason reason;

    @NotBlank(message = "Description should not be empty")
    private String description;

    @NotBlank(message = "Departure date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotBlank(message = "Arrival date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotBlank(message = "Status date should not be empty")
    private Status status;

    @NotBlank(message = "Departure city should not be empty")
    private Long departureCity;

    @NotBlank(message = "Arrival city should not be empty")
    private Long arrivalCity;

}
