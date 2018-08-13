package com.siemens.ct.bam.main;

import com.siemens.ct.bam.broker.DataBroker;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) throws Exception {

        DataBroker dataBroker = new DataBroker();

        dataBroker.receivingData();
    }
}
