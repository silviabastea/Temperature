package com.siemens.ct.bam.broker;

import com.google.gson.Gson;
import com.siemens.ct.bam.commons.MessageConsumerFromBrokerService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.commons.constsnts.Constants;
import com.siemens.ct.bam.commons.models.RequestType;
import com.siemens.ct.bam.temperature.measurement.status.TemperatureAverage;
import com.siemens.ct.bam.temperature.measurement.status.Weather;

public class DataService {

    Weather weather = new Weather();
    TemperatureAverage temperatureAverage = new TemperatureAverage();

    public void addListenerForRequests(){

        BrokerService.getInstance().receiveMessage(Constants.COMMAND, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {
                RequestType request = new Gson().fromJson(s, RequestType.class);
                    executeRequest(request);
            }
        });


    }

    public void executeRequest(RequestType request){
        if (request.getRequest().equals(RequestType.TEMPERATURE_REQUEST)) {
            String temperatureMeasurement = new Gson().toJson(weather.getWeather(request.getCitySpecified()));
            BrokerService.getInstance().sendMessage(temperatureMeasurement, Constants.TEMPERATURE_MEASUREMENT);
        } else if (request.getRequest().equals(RequestType.START_AVERAGE)) {
            while(!(request.getRequest().equals(RequestType.STOP_AVERAGE))) {
                String average = new Gson().toJson(temperatureAverage.getAverage(request.getCitySpecified(), request.getMeasurementInterval(), request.getReportInterval()));
                BrokerService.getInstance().sendMessage(average, Constants.AVERAGE_TEMPERATURE);
            }
        } else if (request.getRequest().equals(RequestType.STOP_AVERAGE)) {
            //--------------------------------- !!!!!!!!!!!!!!!!!!!!!!!!!!!! ----------------------------------
        }
    }
}
