package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VelibStation {

    public VelibStation(){}

    private String stationName;
    private String stationId;
    private Integer totalVelibAvailable;
    private Integer mechanicalBikeAvailable;
    private Integer electricBikeAvailable;
}
