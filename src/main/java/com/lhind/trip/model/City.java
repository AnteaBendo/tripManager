package com.lhind.trip.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Airport> airportList;

    @OneToMany(mappedBy = "departureCity")
    private List<Trip> tripsFromCity;

    @OneToMany(mappedBy = "arrivalCity")
    private List<Trip> tripsToCity;
}
