package com.velib.api.client.controller;

import com.velib.api.client.VelibApiClient;
import com.velib.config.SplioAppTestConfig;
import com.velib.model.exercice1.BikeType;
import com.velib.model.exercice1.Data;
import com.velib.model.exercice1.Station;
import com.velib.model.exercice1.StationInformation;
import com.velib.model.exercice1.StationStat;
import com.velib.model.exercice1.StationStatus;
import com.velib.model.exercice1.StatusData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureMockMvc
@Import(SplioAppTestConfig.class)
public class TestSplioAppController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VelibApiClient velibApiClientMock;

    @Test
    public void testGetVelibStatus() throws Exception {

        BikeType bikeType = BikeType.builder()
                .ebike(5)
                .mechanical(7)
                .build();

        StationStat stationStat = StationStat.builder()
                .is_installed(1)
                .is_renting(1)
                .is_returning(1)
                .last_reported(123)
                .num_bikes_available(12)
                .numBikesAvailable(12)
                .num_bikes_available_types(Collections.singletonList(bikeType))
                .station_id(646L)
                .build();

        StatusData statusData = StatusData.builder()
                .stations(Collections.singletonList(stationStat))
                .build();

        StationStatus stationStatus = StationStatus.builder()
                .lastUpdatedOther(1316)
                .ttl(5465)
                .data(statusData)
                .build();

        Station station = Station.builder()
                .station_id(646L)
                .capacity(2316)
                .lat(48.8706875)
                .lon(2.3382726)
                .name("Splio Station")
                .build();

        Data data = Data.builder()
                .stations(Collections.singletonList(station))
                .build();

        StationInformation stationInformation = StationInformation.builder()
                .data(data)
                .ttl(21321)
                .lastUpdatedOther(745636)
                .build();

        when(velibApiClientMock.getStationsInformation()).thenReturn(stationInformation);
        when(velibApiClientMock.getStationsStatus()).thenReturn(stationStatus);

        mockMvc.perform(get("/splio-app/real-time/velib-status/{distance}", 5))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("content-type", "application/json"))
                .andExpect(jsonPath("address", is("27 Boulevard des Italiens, 75002 Paris")))
                .andExpect(jsonPath("message", is("List Of velib stations within "+ 5.0 + " km radius")))
                .andExpect(jsonPath("velibStations[0].stationName", is("Splio Station")))
                .andExpect(jsonPath("velibStations[0].numberOfMechanicalBikes", is(7)))
                .andExpect(jsonPath("velibStations[0].numberOfElectricBikes", is(5)))
                .andExpect(jsonPath("velibStations[0].numberOfBikesAvailable", is(12)));
    }
}
