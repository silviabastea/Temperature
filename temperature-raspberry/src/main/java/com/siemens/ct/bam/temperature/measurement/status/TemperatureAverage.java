package com.siemens.ct.bam.temperature.measurement.status;

public class TemperatureAverage {

    Double average = 0.0;
    Weather temperatureWeather = new Weather();
    int index = 0;

    public String getAverage() {

        while (index < 5) {

            //average = average + temperatureWeather.getWeather();
            index++;
        }
        average = average / 5;
        index = 0;
        return average.toString();
    }


}
