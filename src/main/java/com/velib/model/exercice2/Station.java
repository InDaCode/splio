package com.velib.model.exercice2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Station {

    private String StationName;
    private Integer numberOfBikesAvailable;
    private Integer numberOfDocksAvailable;
}
