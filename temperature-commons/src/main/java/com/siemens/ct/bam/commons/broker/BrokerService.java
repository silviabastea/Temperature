package com.siemens.ct.bam.commons.broker;

import com.rabbitmq.client.*;
import com.siemens.ct.bam.commons.MessageConsumerFromBrokerService;
import com.siemens.ct.bam.commons.constsnts.Constants;
import com.siemens.ct.bam.commons.file.ReadPropertyFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BrokerService {

    private static BrokerService instance = null;
    private Connection connection;
    private Channel channel;

    private BrokerService() {

    }

    public static BrokerService getInstance() {
        if (instance == null) {
            instance = new BrokerService();
        }
        return instance;
    }

    public void connect2Broker(){
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(ReadPropertyFile.getHost());
            factory.setUsername(ReadPropertyFile.getUsername());
            factory.setPassword(ReadPropertyFile.getPassword());

            connection = factory.newConnection();
            channel = connection.createChannel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromBroker() {
        if (connection != null)
            try {
                connection.close();
                channel.close();
            } catch (Exception ignore) {
            }
    }

    public void sendMessage(String message, String routingKey) {
        try {
            channel.exchangeDeclare(Constants.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            channel.basicPublish(Constants.EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ignore) {
        }
    }

    public void receiveMessage(String routingKey, MessageConsumerFromBrokerService messageConsumerFromBrokerService){

        try {
            channel.exchangeDeclare(Constants.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, Constants.EXCHANGE_NAME, routingKey);


            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    messageConsumerFromBrokerService.onNewMessage(message);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception ignore){}
    }

}
