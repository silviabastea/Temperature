package com.siemens.ct.bam.GUI;

import com.google.gson.Gson;
import com.siemens.ct.bam.commons.MessageConsumerFromBrokerService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.commons.constsnts.Constants;
import com.siemens.ct.bam.commons.models.RequestType;
import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureMeasurementUI extends JDialog {

    private JPanel MeasurementPanel;
    private JComboBox cityNames;
    private JButton buttonGetTemp;
    private JTextArea temperatureMessages;
    private JTextArea avgMessages;
    private JTextField measurementIntervalField;
    private JTextField reportIntervalField;
    private JButton buttonOnOffAvg;

    public TemperatureMeasurementUI() {
        this.setMinimumSize(new Dimension(500, 500));
        setContentPane(MeasurementPanel);
        setModal(true);
        temperatureMessages.setPreferredSize(new Dimension(20, 20));


        cityNames.setModel(new DefaultComboBoxModel(UIUtil.getCityNames().toArray()));


        buttonGetTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cityNameFromComboBox = (String) cityNames.getSelectedItem();
                RequestType request = new RequestType(RequestType.TEMPERATURE_REQUEST, cityNameFromComboBox);

                String message = new Gson().toJson(request);
                BrokerService.getInstance().sendMessage(message, Constants.COMMAND);
                temperatureMessages.setText("");
            }
        });


        BrokerService.getInstance().receiveMessage(Constants.TEMPERATURE_MEASUREMENT, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {

//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
                if (s != null) {
                    TemperatureMeasurement temperatureMeasurement = new Gson().fromJson(s, TemperatureMeasurement.class);
                    temperatureMessages.setText(temperatureMeasurement.toString());
                } else
                    temperatureMessages.setText("Something don't work. Please check the raspberry.");
            }
        });


    }


    @Override
    public void dispose() {
        super.dispose();
        BrokerService.getInstance().disconnectFromBroker();
    }
}
