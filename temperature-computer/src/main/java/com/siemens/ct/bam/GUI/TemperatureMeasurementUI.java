package com.siemens.ct.bam.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureMeasurementUI extends  JDialog{

    private JPanel measurementPanel;
    private JComboBox cityNames;
    private JButton buttonGetTemp;
    private JList temperatureMessages;

    public TemperatureMeasurementUI() {

        setContentPane(measurementPanel);
        setModal(true);
        cityNames.setModel(new DefaultComboBoxModel(UIUtil.getCityNames().toArray()));


        buttonGetTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cityNameFromComboBox = (String) cityNames.getSelectedItem();


            }
        });






    }
}
