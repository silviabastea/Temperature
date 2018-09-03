package com.siemens.ct.bam.GUI;

import com.siemens.ct.bam.commons.broker.BrokerService;

public class MainUI {


    public static void main(String[] args) {

        BrokerService.getInstance().connect2Broker();

        TemperatureMeasurementUI dialog = new TemperatureMeasurementUI();
        dialog.pack();
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
