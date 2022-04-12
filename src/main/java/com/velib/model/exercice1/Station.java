package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Station {

    public Station(){}

    private Integer capacity;
    private Double lat;
    private Double lon;
    private String name;
    private Long station_id;
}
