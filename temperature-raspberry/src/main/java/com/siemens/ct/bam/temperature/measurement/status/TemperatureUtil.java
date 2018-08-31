package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

public class TemperatureUtil {

    Location location = new Location();
    Weather weather = new Weather();

    TemperatureMeasurement temperatureMeasurement;
    String city;
    Double temperature;

    public TemperatureMeasurement getTemperatureMeasurement() {


        city = location.getCurrentCity();
        temperature = weather.getWeather();

        temperatureMeasurement = new TemperatureMeasurement(city, temperature);

        return temperatureMeasurement;
    }


}
