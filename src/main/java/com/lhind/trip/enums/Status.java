package com.lhind.trip.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    CREATED("CREATED"),
    WAITING_FOR_APROVAL("WAITING_FOR_APROVAL"),
    APPROVED("APPROVED");

    private String status;


}
