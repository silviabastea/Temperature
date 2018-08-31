package com.siemens.ct.bam.commons.models;

public class TemperatureMeasurement {

    String currentCity;
    Double currentTemperature;
    String status;
    String errorReport;

    public TemperatureMeasurement(String currentCity, Double currentTemperature) {
        this.currentCity = currentCity;
        this.currentTemperature = currentTemperature;
        this.status = "successful";
    }

    public TemperatureMeasurement(String currentCity, Double currentTemperature, String errorReport) {
        this.currentCity = currentCity;
        this.currentTemperature = currentTemperature;
        this.status = "failed";
        this.errorReport = errorReport;
    }


    public String getCurrentCity() {
        return currentCity;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "In " + currentCity + " are " + currentTemperature + " degrees" + "action was: " + status + " .";
    }
}
