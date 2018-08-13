package com.siemens.ct.bam.main;

import com.siemens.ct.bam.broker.DataBroker;
import com.siemens.ct.bam.commons.Temperature;
import com.siemens.ct.bam.temperature.status.Degrees;
import com.siemens.ct.bam.temperature.status.TemperatureUtil;

import java.io.IOException;

public class Main {
    public static boolean condition = true;

    public static void main(String[] args) throws Exception {

        TemperatureUtil temperatureUtil = new TemperatureUtil();
        DataBroker dataBroker = new DataBroker();

        Thread secondThread = new Thread(() -> {
            while(condition){
                Temperature temperature = temperatureUtil.getTemperature();
                dataBroker.sendingData(temperature);
            }
        });
        secondThread.start();

        System.in.read();
        condition = false;

    }
}
