package com.siemens.ct.bam.temperature.measurement.status;

import com.siemens.ct.bam.commons.models.TemperatureAverage;
import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

import java.util.concurrent.TimeUnit;

public class Average {


    Weather weather = new Weather();


    public TemperatureAverage getAverage(String location, Integer frequency, Integer period){

        int index = 0;
        Double average = 0.0;
        while(index < (period / frequency))
        {
            TemperatureMeasurement temperatureMeasurement = weather.getWeather(location);
            average = average + temperatureMeasurement.getCurrentTemperature();
            index++;

            try {
                TimeUnit.SECONDS.sleep(frequency);
            } catch (InterruptedException ignore) {

            }
        }
        average = average / index;

        TemperatureAverage temperatureAverage = new TemperatureAverage(average);

        return temperatureAverage;
    }


}
