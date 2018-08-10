package com.siemens.ct.bam.commons;

public class Temperature {

    String currentCity;
    Double currentTemperature;

    public Temperature() {
    }

    public Temperature(String currentCity, Double currentTemperature) {
        this.currentCity = currentCity;
        this.currentTemperature = currentTemperature;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    @Override
    public String toString() {
        return "In " + currentCity + " are " + currentTemperature + "degrees";
    }
}
