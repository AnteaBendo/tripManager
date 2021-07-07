package com.lhind.trip.repository;

import com.lhind.trip.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    public List<Airport> findByCityId(Long id);
}
