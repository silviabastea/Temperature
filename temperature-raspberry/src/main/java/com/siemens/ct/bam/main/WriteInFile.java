package com.siemens.ct.bam.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {

    File FILE_NAME = new File("content.txt");
    BufferedWriter bw;

    public void writeContentInFile(String content) {
        try {
            bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
