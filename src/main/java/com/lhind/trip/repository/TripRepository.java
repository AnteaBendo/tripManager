package com.lhind.trip.repository;

import com.lhind.trip.enums.Status;
import com.lhind.trip.model.Trip;
import com.lhind.trip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUser(User user);
    List<Trip> findTripByStatus(Status status);
}
