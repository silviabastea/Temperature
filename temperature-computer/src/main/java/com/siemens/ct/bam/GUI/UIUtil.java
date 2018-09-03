package com.siemens.ct.bam.GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class UIUtil {

    public static TreeSet<String> getCityNames(){

        Set<String> cityNames = new TreeSet<>();
        cityNames.add("");

        try {
            BufferedReader br = new BufferedReader(new FileReader("city_names.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                cityNames.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (TreeSet<String>) cityNames;
    }


}
