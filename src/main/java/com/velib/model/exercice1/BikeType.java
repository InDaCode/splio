package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BikeType {
    public BikeType(){}

    private Integer ebike;
    private Integer mechanical;

}
