package com.siemens.ct.bam.main;


import com.siemens.ct.bam.broker.DataService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.rest.services.TemperatureClient;

import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws Exception {
/*
        BrokerService.getInstance().connect2Broker();

        TimeUnit.SECONDS.sleep(10);
        DataService dataService = new DataService();
        dataService.addListenerForRequests();


        TimeUnit.SECONDS.sleep(15);
        BrokerService.getInstance().disconnectFromBroker();

*/

        TemperatureClient temperatureClient = new TemperatureClient();
        WriteInFile writer = new WriteInFile();

       String content = temperatureClient.getContent();


        Integer nameIndex = content.indexOf("nume");

        String place  = content.substring(nameIndex +1, nameIndex + 29).trim();
        System.out.println(place);
    }
}
