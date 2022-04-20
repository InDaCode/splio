package com.velib.model.exercice1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StationStat {

    public StationStat(){}

    @JsonProperty("is_installed")
    private Integer isInstalled;
    private Integer is_renting;
    private Integer is_returning;
    private Integer last_reported;
    private Integer numBikesAvailable;
    private Integer numDocksAvailable;
    private Integer num_bikes_available;
    private List<BikeType> num_bikes_available_types;
    private Integer num_docks_available;
    private Long station_id;
}
