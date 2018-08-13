package com.siemens.ct.bam.broker;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import com.siemens.ct.bam.commons.ReadPropertyFile;
import com.siemens.ct.bam.commons.Temperature;

import java.awt.*;
import java.io.IOException;

public class DataBroker {

    private static final String EXCHANGE_NAME = ReadPropertyFile.getExchangeName();

    public void receivingData() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ReadPropertyFile.getHost());
        factory.setUsername(ReadPropertyFile.getUsername());
        factory.setPassword(ReadPropertyFile.getPassword());

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();

        String routingKey = ReadPropertyFile.getRoutingKey();
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumeTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                Temperature currentTemperature = (Temperature) new Gson().fromJson(message, Temperature.class);
                System.out.println("[x]Received' " + currentTemperature + "'.");
            }
        };
        channel.basicConsume(queueName,true,consumer);
        System.out.println("[x]Waiting for messages. Press ENTER to close.");

        System.in.read();
        channel.close();
        connection.close();
    }
}
