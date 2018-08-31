package com.siemens.ct.bam.ui;

public class Main {
    public static void main(String[] args) {


        MainForm dialog = new MainForm();
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        System.exit(0);


    }
}
