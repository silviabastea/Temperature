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

    public TemperatureMeasurement(String errorReport) {
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

    public String getErrorReport() {
        return errorReport;
    }

    @Override
    public String toString() {
        return "In " + currentCity + " is a temperature of " + currentTemperature + " degrees.";
    }
}
