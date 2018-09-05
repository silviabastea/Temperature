package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

public class TemperatureAverage {


    Weather weather = new Weather();


    public Double getAverage(String location, Double frequency, Double period) {

        int index = 0;
        Double average = 0.0;
        while(index < (int)(period / frequency))
        {
            TemperatureMeasurement temperatureMeasurement = weather.getWeather(location);
            average = average + temperatureMeasurement.getCurrentTemperature();
            index++;
        }
        average = average / index;
        return average;
    }


}
