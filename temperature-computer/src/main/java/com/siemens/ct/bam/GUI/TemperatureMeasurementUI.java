package com.siemens.ct.bam.GUI;

import com.siemens.ct.bam.broker.DataService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.commons.models.RequestType;
import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class TemperatureMeasurementUI extends JDialog {

    private JPanel measurementPanel;
    private JComboBox cityNames;
    private JButton buttonGetTemp;
    private JTextArea temperatureMessages;

    DataService dataService = new DataService();

    public TemperatureMeasurementUI() {
        this.setMinimumSize(new Dimension(500, 500));
        setContentPane(measurementPanel);
        setModal(true);
        cityNames.setModel(new DefaultComboBoxModel(UIUtil.getCityNames().toArray()));


        buttonGetTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cityNameFromComboBox = (String) cityNames.getSelectedItem();
                RequestType request = new RequestType(RequestType.TEMPERATURE_REQUEST, cityNameFromComboBox);

                dataService.sendRequest(request);
            }
        });
/*
        temperatureMessages.setPreferredSize(new Dimension(20,20));
        TemperatureMeasurement temperatureMeasurement = dataService.receiveTemperatureMeasurement();
        if (temperatureMeasurement == null)
            temperatureMessages.setText("Nothing yet");
        else {
            temperatureMessages.setText(temperatureMeasurement.toString());
        }
*/
    }

    @Override
    public void dispose() {
        super.dispose();
        BrokerService.getInstance().disconnectFromBroker();
    }
}
