package com.siemens.ct.bam.commons.models;

public class TemperatureAverage {

    private double TemperatureAverage;

    public TemperatureAverage(double temperatureAverage) {
        TemperatureAverage = temperatureAverage;
    }

    @Override
    public String toString() {
        return "Temperature in last hours is: " + TemperatureAverage + " .";
    }
}
