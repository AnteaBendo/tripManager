package com.lhind.trip.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Reason {
    MEETING("MEETING"),
    TRAINING("TRAINING"),
    PROJECT("PROJECT"),
    WORKSHOP("WORKSHOP"),
    EVENT("EVENT"),
    OTHER("OTHER");

    private String reason;
}
