package com.siemens.ct.bam.GUI;

import com.google.gson.Gson;
import com.siemens.ct.bam.commons.MessageConsumerFromBrokerService;
import com.siemens.ct.bam.commons.broker.BrokerService;
import com.siemens.ct.bam.commons.constsnts.Constants;
import com.siemens.ct.bam.commons.models.RequestType;
import com.siemens.ct.bam.commons.models.TemperatureAverage;
import com.siemens.ct.bam.commons.models.TemperatureMeasurement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class TemperatureMeasurementUI extends JDialog {

    public static final int CONVERTED_HOUR2SECONDS = 3600;
    public static final int CONVERTED_MINUTE2SECONDS = 60;
    private JPanel MeasurementPanel;
    private JComboBox cityNames;
    private JButton buttonGetTemp;
    private JTextArea temperatureMessages;
    private JTextArea avgMessages;
    private JButton buttonOnOffAvg;
    private JTextField hoursMeasurementField;
    private JTextField minutesMeasurementField;
    private JTextField secondsMeasurementField;
    private JTextField hoursReportField;
    private JTextField minutesReportField;
    private JTextField secondsReportField;


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
                if (cityNameFromComboBox == "")
                {
                    JOptionPane.showMessageDialog(null, "Please select a city to can receive the temperature measurement.");
                    return;
                }
                RequestType request = new RequestType(RequestType.TEMPERATURE_REQUEST, cityNameFromComboBox);

                String message = new Gson().toJson(request);
                BrokerService.getInstance().sendMessage(message, Constants.COMMAND);
                temperatureMessages.setText("");
            }
        });


        BrokerService.getInstance().receiveMessage(Constants.TEMPERATURE_MEASUREMENT, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {
//                if (temperatureMessages.getText().equals(null))
//                    temperatureMessages.setText("Something don't work. Please check the raspberry.");
                TemperatureMeasurement temperatureMeasurement = new Gson().fromJson(s, TemperatureMeasurement.class);
                if(temperatureMeasurement.getStatus().equals(TemperatureMeasurement.NEGATIVE_STATUS))
                {
                //------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-----------------------------------
                }
                temperatureMessages.setText(temperatureMeasurement.toString());
            }

        });



        buttonOnOffAvg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cityNameFromComboBox = (String) cityNames.getSelectedItem();

                if (cityNameFromComboBox == "")
                {
                    JOptionPane.showMessageDialog(null, "Please select a city to can start receiving average.");
                    return;
                }

                if (buttonOnOffAvg.getText().equals("Start")) {

                    Integer hoursMeasurementInterval = Integer.parseInt(hoursMeasurementField.getText());
                    Integer minutesMeasurementInterval = Integer.parseInt(minutesMeasurementField.getText());
                    Integer secondMeasurementInterval = Integer.parseInt(secondsMeasurementField.getText());

                    Integer measurementInterval = hoursMeasurementInterval * CONVERTED_HOUR2SECONDS + minutesMeasurementInterval * CONVERTED_MINUTE2SECONDS + secondMeasurementInterval;

                    Integer hoursReportInterval = Integer.parseInt(hoursReportField.getText());
                    Integer minutesReportInterval = Integer.parseInt(minutesReportField.getText());
                    Integer secondsReportInterval = Integer.parseInt(secondsReportField.getText());

                    Integer reportInterval = hoursReportInterval * CONVERTED_HOUR2SECONDS + minutesReportInterval * CONVERTED_MINUTE2SECONDS + secondsReportInterval;


                    if(measurementInterval == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Please insert a measurement interval.");
                        return;
                    }

                    if(reportInterval == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Please insert a report interval.");
                        return;
                    }

                    if(measurementInterval > reportInterval)
                    {
                        JOptionPane.showMessageDialog(null, "Measurement interval must be smaller than report interval!");
                        return;
                    }


                        RequestType request = new RequestType(RequestType.START_AVERAGE, cityNameFromComboBox, measurementInterval, reportInterval);
                        String message = new Gson().toJson(request);
                        BrokerService.getInstance().sendMessage(message, Constants.COMMAND);
                        buttonOnOffAvg.setText("Stop");

                } else {
                    RequestType request = new RequestType(RequestType.STOP_AVERAGE, cityNameFromComboBox);
                    String message = new Gson().toJson(request);
                    BrokerService.getInstance().sendMessage(message,Constants.COMMAND);
                    buttonOnOffAvg.setText("Start");
                    avgMessages.setText("");
                }

            }
        });

        BrokerService.getInstance().receiveMessage(Constants.AVERAGE_TEMPERATURE, new MessageConsumerFromBrokerService() {
            @Override
            public void onNewMessage(String s) {

                TemperatureAverage temperatureAverage = new Gson().fromJson(s, TemperatureAverage.class);
                avgMessages.setText(temperatureAverage.toString());

            }
        });
    }


    @Override
    public void dispose() {
        super.dispose();
        BrokerService.getInstance().disconnectFromBroker();
    }
}
