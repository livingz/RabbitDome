package com.zou.mqdemo.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Zouwx
 * @create 2020-04-05 19:40
 * @desc
 **/
public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String severity = getSeverity(argv);
            String message = getMessage(argv);

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }

    private static String getMessage(String[] argv) {
        return argv.length < 1 ? "info: Hello World!" :
                String.join(" ", argv);
    }

    private static String getSeverity(String[] strings) {
        if (strings.length<1){
            return "info";
        }
        return strings[0];
    }
}
