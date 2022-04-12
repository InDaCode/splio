package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;

@lombok.Data
@Builder
@AllArgsConstructor
public class StationInformation {
    public StationInformation(){}

    private Integer lastUpdatedOther;
    private Integer ttl;
    private Data data;

}
