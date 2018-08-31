package com.siemens.ct.bam.commons;

public interface MessageConsumerFromBrokerService {

    void onNewMessage(String message);

}
