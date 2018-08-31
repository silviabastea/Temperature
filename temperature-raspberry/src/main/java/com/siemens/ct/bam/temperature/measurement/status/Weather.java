package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.commons.models.TemperatureMeasurement;
import com.siemens.ct.bam.rest.services.TemperatureClient;

public class Weather {

    TemperatureClient temperatureClient = new TemperatureClient();


    String weather;
    TemperatureMeasurement temperatureMeasurement;

    public TemperatureMeasurement getWeather(String location) {

        String content = temperatureClient.getContent();
        Integer locationLength = location.length();

        Integer locationIndex = content.indexOf(location) + locationLength;

        weather = content.substring(locationIndex + 11, locationIndex + 15).trim();
        temperatureMeasurement = new TemperatureMeasurement(location, Double.parseDouble(weather));
        return temperatureMeasurement;

    }
}
