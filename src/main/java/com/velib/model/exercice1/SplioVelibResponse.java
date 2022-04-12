package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SplioVelibResponse {

    private String address;
    private String message;
    private List<SplioVelibStation> velibStations;

}
