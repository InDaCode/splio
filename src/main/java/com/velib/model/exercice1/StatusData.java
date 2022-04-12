package com.velib.model.exercice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StatusData {

    public StatusData(){}

    private List<StationStat> stations;
}
