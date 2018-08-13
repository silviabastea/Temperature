package com.siemens.ct.bam.temperature.status;

import com.siemens.ct.bam.commons.Temperature;

public class TemperatureUtil {


    public Temperature getTemperature() {

        Temperature temperature = new Temperature();
        City city = new City();
        Degrees degrees = new Degrees();

        temperature.setCurrentCity(city.getCurrentCity());
        temperature.setCurrentTemperature(Double.parseDouble(degrees.getDegrees()));

        return  temperature;
    }


}
