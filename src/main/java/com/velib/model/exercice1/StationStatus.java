package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StationStatus {

    public StationStatus(){}

    private StatusData data;
    private Integer lastUpdatedOther;
    private Integer ttl;
}
