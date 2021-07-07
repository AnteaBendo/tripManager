package com.lhind.trip.model;

import com.lhind.trip.enums.Reason;
import com.lhind.trip.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trip")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Column
    private String description;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalDate arrivalDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "departure_city", referencedColumnName = "id")
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city", referencedColumnName = "id")
    private City arrivalCity;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "trip")
    private List<Flight> flightsOfTrip;

}
