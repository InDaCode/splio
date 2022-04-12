package com.velib;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velib.model.exercice2.Station;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
public class VelibDataSetFormatter {
    private String sourceFile;

    public VelibDataSetFormatter(String sourceFilePath){
        sourceFile = sourceFilePath;
    }

    private final Map<Integer, List<Station>> formattedDataSet = new HashMap<>();

    public Map<Integer, List<Station>> formatDataSet() {
        // Data for each station per Hour and station Name
        try (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            stream.forEach(this::formatDataSet);
        } catch (IOException e) {
            log.error("Failed to format data set", e);
        }
        return formattedDataSet;
    }

    private void formatDataSet(String jsonLine) {

        List<Station> stationsList = new ArrayList<>();

        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(jsonLine);
        } catch (JsonProcessingException e) {
            log.error("Failed to read JSON node tree", e);
        }

        if (rootNode != null) {
            if (rootNode.get("records") != null) {
                if (rootNode.get("records").get(0) != null) {
                    String date = rootNode.get("records").get(0).get("record_timestamp").asText();
                    Integer hour = DateTime.parse(date).getHourOfDay();
                    Iterator<JsonNode> records = rootNode.get("records").elements();

                    while (records.hasNext()) {
                        JsonNode record = records.next();
                        Station station = Station.builder()
                                .StationName(record.get("fields").get("station_name").asText())
                                .numberOfBikesAvailable(record.get("fields").get("nbbike").asInt())
                                .numberOfDocksAvailable(record.get("fields").get("nbfreedock").asInt())
                                .build();

                        stationsList.add(station);
                    }

                    if (formattedDataSet.containsKey(hour)) {
                        stationsList.addAll(formattedDataSet.get(hour));
                        formattedDataSet.put(hour, stationsList);
                    } else {
                        formattedDataSet.put(hour, stationsList);
                    }
                }
            }
        }
    }
}
