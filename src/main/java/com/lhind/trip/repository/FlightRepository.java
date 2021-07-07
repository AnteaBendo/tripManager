package com.lhind.trip.repository;

import com.lhind.trip.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value ="SELECT * FROM Flight " +
                    "WHERE (departureDate >= ? AND departureDate < ?) " +
                            "OR (landingDate > ? AND landingDate < ?) " +
                            "AND Trip.id = ?", nativeQuery = true)
    List<Flight> flightsInBetween(LocalDate departure, LocalDate landing, LocalDate departure1, LocalDate landing1, Long id);
}
