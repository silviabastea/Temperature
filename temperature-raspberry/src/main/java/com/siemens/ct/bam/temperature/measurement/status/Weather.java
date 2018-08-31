package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.rest.services.TemperatureClient;

public class Weather {

    Location location = new Location();
    TemperatureClient temperatureClient = new TemperatureClient();


    String weather;

    public Double getWeather() {


        String content = temperatureClient.getContent();
        String city = location.getCurrentCity();
        Integer cityLength = city.length();

        Integer locationIndex = content.indexOf(city) + cityLength;

        weather = content.substring(locationIndex + 11, locationIndex + 15).trim();
        return Double.parseDouble(weather);

    }
}
