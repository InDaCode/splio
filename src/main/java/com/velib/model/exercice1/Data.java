package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
@lombok.Data
@Builder
@AllArgsConstructor
public class Data {

    public Data(){}
    private List<Station> stations;

}
