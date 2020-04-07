package com.zou.mqdemo.commit;

import com.zou.mqdemo.config.RabbitConfigure;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zouwx
 * @create 2020-04-05 12:31
 * @desc Sender
 **/
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend(RabbitConfigure.SIMPLE_QUEUE, message);
    }

    public void worker(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend(RabbitConfigure.WORK_QUEUE, message);
    }

    public void fanout(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("fanoutExchange","", message);
    }

    public void direct1(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("directExchange","directTopic1", message);
    }

    public void direct2(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("directExchange","directTopic2", message);
    }

    public void topic1(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("topicExchange","person.nihao.save", message);
    }
    public void topic2(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("topicExchange","person.hah.ins", message);
    }
    public void topic3(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("topicExchange","human.del", message);
    }
    public void topic4(String message) {
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend("topicExchange","human.select.byUserId", message);
    }
}
