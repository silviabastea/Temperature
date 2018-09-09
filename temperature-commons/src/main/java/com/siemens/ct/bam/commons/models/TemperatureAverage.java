package com.siemens.ct.bam.commons.models;

public class TemperatureAverage {

    private double TemperatureAverage;

    public TemperatureAverage(double temperatureAverage) {
        TemperatureAverage = temperatureAverage;
    }

    public double getTemperatureAverage() {
        return TemperatureAverage;
    }

    @Override
    public String toString() {
        return "Temperature average is: " + TemperatureAverage + " .";
    }
}
