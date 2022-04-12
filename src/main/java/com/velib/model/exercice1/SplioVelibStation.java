package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SplioVelibStation {

    private String stationName;
    private Integer numberOfBikesAvailable;
    private Integer numberOfMechanicalBikes;
    private Integer numberOfElectricBikes;
}
