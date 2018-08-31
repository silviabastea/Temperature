package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.rest.services.TemperatureClient;

public class Weather {

    Location location = new Location();
    TemperatureClient temperatureClient = new TemperatureClient();


    String weather;

    public Double getWeather() {


        String content = temperatureClient.getContent();
        Integer locationIndex = content.indexOf(location.getCurrentCity());

        weather = content.substring(locationIndex + 25, locationIndex + 29).trim();
        return Double.parseDouble(weather);

    }
}
