package com.velib.api.client;

import com.velib.model.exercice1.StationInformation;
import com.velib.model.exercice1.StationStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class VelibApiClient {

    private static final String STATION_INFORMATION_URL = "https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_information.json";
    private static final String STATION_STATUS_URL = "https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_status.json";

    RestTemplate restTemplate = new RestTemplate();

    public StationInformation getStationsInformation() throws WebClientResponseException {
        return restTemplate.getForEntity(STATION_INFORMATION_URL, StationInformation.class).getBody();
}

    public StationStatus getStationsStatus() throws WebClientResponseException {
        return restTemplate.getForEntity(STATION_STATUS_URL, StationStatus.class).getBody();
    }

}
