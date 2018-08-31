package com.siemens.ct.bam.GUI;

public class MainUI {


    public static void main(String[] args) {

        TemperatureMeasurementUI dialog = new TemperatureMeasurementUI();
        dialog.pack();
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
