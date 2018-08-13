package com.siemens.ct.bam.broker;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.siemens.ct.bam.commons.ReadPropertyFile;
import com.siemens.ct.bam.commons.Temperature;

import java.util.concurrent.TimeUnit;

public class DataBroker {

    static final String EXCHANGE_NAME = ReadPropertyFile.getExchangeName();

    public static void sendingData(Temperature currentTemperature) {
        Connection connection = null;
        Channel channel = null;

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(ReadPropertyFile.getHost());
            factory.setUsername(ReadPropertyFile.getUsername());
            factory.setPassword(ReadPropertyFile.getPassword());


            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String routingKey = ReadPropertyFile.getRoutingKey();

            String objectConvertedToSTring = new Gson().toJson(currentTemperature);

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, objectConvertedToSTring.getBytes("UTF-8"));


            System.out.println("[x] Sent '" + objectConvertedToSTring + "'. Press ENTER to stop.");

            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                    channel.close();
                } catch (Exception ignore) {

                }
        }
    }
}
