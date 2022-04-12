package com.velib.controller;

import com.velib.GeoUtil;
import com.velib.VelibDataSetFormatter;
import com.velib.api.client.VelibApiClient;
import com.velib.model.exercice1.SplioVelibResponse;
import com.velib.model.exercice1.Station;
import com.velib.model.exercice1.StationInformation;
import com.velib.model.exercice1.StationStat;
import com.velib.model.exercice1.StationStatus;
import com.velib.model.exercice1.mapper.SplioVelibResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/splio-app")
@Slf4j
public class SplioAppController {

    // Coordinates for the address : 27 Boulevard des Italiens, 75002 Paris
    private final static Double LAT = 48.87110230451192;
    private final static Double LON = 2.33535951179537;

    @Autowired
    private VelibApiClient velibApiClient;
    @Autowired
    private VelibDataSetFormatter velibDataSetFormatter;
    @Autowired
    private SplioVelibResponseMapper splioVelibResponseMapper;

    /**
     * @param distance distance from your current position in kilometers. example: distance = 0.2 km
     *                 the position is hard coded (27 Boulevard des Italiens, 75002 Paris)
     * @return SplioVelibResponse
     */
    @GetMapping("/real-time/velib-status/{distance}")
    public ResponseEntity<SplioVelibResponse> getVelibStatus(@PathVariable Double distance) {
        log.info("Receiving request to get velib status per stations within " + distance + " km radius");

        StationInformation stationInformation = velibApiClient.getStationsInformation();

        Map<Long, Station> nearestVelibStations = stationInformation.getData().getStations().stream()
                .filter(station -> GeoUtil.computeDistanceInKilometers(LAT, LON, station.getLat(), station.getLon()) < distance)
                .collect(Collectors.toMap(Station::getStation_id, station -> station));

        StationStatus stationsStatus = velibApiClient.getStationsStatus();

        Map<Long, StationStat> stationStats = stationsStatus.getData().getStations().stream()
                .filter(stationStat -> nearestVelibStations.containsKey(stationStat.getStation_id()))
                .collect(Collectors.toMap(StationStat::getStation_id, stationStat -> stationStat));

        return ResponseEntity.ok().body(splioVelibResponseMapper.mapStationStationStatToSplioVelibResponse(nearestVelibStations, stationStats, distance));
    }

    /**
     * @param hour the value need to be an integer between 5 and 15. example: hour = 8
     * @return Number Of bikes available per station
     */
    @GetMapping("/velib-data-set/number-of-bikes-available/{hour}")
    public ResponseEntity<Map<String, Double>> getAverageNumberOfBikesAvailablePerHour(@PathVariable Integer hour) {
        log.info("Receiving request to get the average number of available bikes at " + hour);

        Map<String, Double> numberOfBikesAvailablePerHour = velibDataSetFormatter.formatDataSet().get(hour).stream()
                .collect(Collectors.groupingBy(com.velib.model.exercice2.Station::getStationName,
                        Collectors.averagingInt(com.velib.model.exercice2.Station::getNumberOfBikesAvailable)));

        return ResponseEntity.ok().body(numberOfBikesAvailablePerHour);
    }

    /**
     * @param hour the value need to be an integer between 5 and 15. example: hour = 8
     * @return Number Of docks available per station
     */
    @GetMapping("/velib-data-set/number-of-docks-available/{hour}")
    public ResponseEntity<Map<String, Double>> getAverageNumberOfDocksAvailablePerHour(@PathVariable Integer hour) {
        log.info("Receiving request to get the average number of docks available at " + hour);

        Map<String, Double> numberOfDocksAvailablePerHour = velibDataSetFormatter.formatDataSet().get(hour).stream()
                .collect(Collectors.groupingBy(com.velib.model.exercice2.Station::getStationName,
                        Collectors.averagingInt(com.velib.model.exercice2.Station::getNumberOfDocksAvailable)));

        return ResponseEntity.ok().body(numberOfDocksAvailablePerHour);
    }

}
