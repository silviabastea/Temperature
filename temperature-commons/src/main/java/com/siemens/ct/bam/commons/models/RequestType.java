package com.siemens.ct.bam.commons.models;

public class RequestType {

    public static final String TEMPERATURE_REQUEST = "Get temperature";
    public static final String START_AVERAGE = "Get average";
    public static final String STOP_AVERAGE = "Stop average";


    private String request;
    String citySpecified;
    Double measurementInterval;
    Double reportInterval;

    public RequestType(String request, String citySpecified) {
        this.request = request;
        this.citySpecified = citySpecified;
    }

    public RequestType(String request, String citySpecified, Double measurementInterval, Double reportInterval) {
        this.request = request;
        this.citySpecified = citySpecified;
        this.measurementInterval = measurementInterval;
        this.reportInterval = reportInterval;
    }

    public String getRequest() {
        return request;
    }

    public String getCitySpecified() {
        return citySpecified;
    }

    public Double getMeasurementInterval() {
        return measurementInterval;
    }

    public Double getReportInterval() {
        return reportInterval;
    }

    @Override
    public String toString() {
        return "Request is: " + request + ", for city" + citySpecified +  " .";
    }
}
