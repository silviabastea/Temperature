package com.siemens.ct.bam.broker;


import com.google.gson.Gson;
import com.siemens.ct.bam.commons.MessageConsumerFromBrokerService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.commons.constsnts.Constants;
import com.siemens.ct.bam.commons.models.RequestType;
import com.siemens.ct.bam.commons.models.TemperatureAverage;
import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

public class DataService {

    public void sendRequest(RequestType request) throws Exception {
        String message = new Gson().toJson(request);
        BrokerService.getInstance().sendMessage(message, Constants.COMMAND);
    }

    public TemperatureMeasurement receiveTemperatureMeasurement() throws Exception {
        final String[] message = {null};
        BrokerService.getInstance().receiveMessage(Constants.TEMPERATURE_MEASUREMENT, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {
                message[0] = s;
            }
        });
        return new Gson().fromJson(message[0], TemperatureMeasurement.class);
    }

    public TemperatureAverage receiveTemperatureAverage() throws Exception {
        final String[] message = {null};
        BrokerService.getInstance().receiveMessage(Constants.AVERAGE_TEMPERATURE, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {
                message[0] = s;
            }
        });
        return new Gson().fromJson(message[0], TemperatureAverage.class);
    }

}
