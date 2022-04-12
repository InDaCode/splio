package com.velib.model.exercice1.mapper;

import com.velib.model.exercice1.BikeType;
import com.velib.model.exercice1.SplioVelibResponse;
import com.velib.model.exercice1.SplioVelibStation;
import com.velib.model.exercice1.Station;
import com.velib.model.exercice1.StationStat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SplioVelibResponseMapper {
    /**
     * @param nearestVelibStations Containing all the stations IDs within the specified radius
     * @param stationStat Containing information related to the number of bikes for the nearest stations
     * @param distance distance from your current position in kilometers
     * @return SplioVelibResponse
     */
    public SplioVelibResponse mapStationStationStatToSplioVelibResponse(Map<Long, Station> nearestVelibStations, Map<Long, StationStat> stationStat, Double distance){

        List<SplioVelibStation> splioVelibStationList = new ArrayList<>();


        nearestVelibStations.forEach((stationId, station) ->
                splioVelibStationList.add(SplioVelibStation.builder()
                        .stationName(station.getName())
                        .numberOfBikesAvailable(stationStat.get(stationId).getNumBikesAvailable())
                        .numberOfElectricBikes(getNumberOfElectricBikes(stationStat.get(stationId).getNum_bikes_available_types()))
                        .numberOfMechanicalBikes(getNumberOfMechanicalBikes(stationStat.get(stationId).getNum_bikes_available_types()))
                        .build())

        );

        return SplioVelibResponse.builder()
                .address("27 Boulevard des Italiens, 75002 Paris")
                .message("List Of velib stations within "+ distance + " km radius")
                .velibStations(splioVelibStationList)
                .build();

    }

    private Integer getNumberOfElectricBikes(List<BikeType> bikeTypes){
        Integer numberOfElectricBikes = 0;

        for(BikeType bikeType: bikeTypes){
            if(bikeType.getEbike() != null){
                numberOfElectricBikes = bikeType.getEbike();
            }
        }

        return numberOfElectricBikes;
    }

    private Integer getNumberOfMechanicalBikes(List<BikeType> bikeTypes){
        Integer numberOfMechanicalBikes = 0;

        for(BikeType bikeType: bikeTypes){
            if(bikeType.getMechanical() != null){
                numberOfMechanicalBikes = bikeType.getMechanical();
            }
        }

        return numberOfMechanicalBikes;
    }
}
