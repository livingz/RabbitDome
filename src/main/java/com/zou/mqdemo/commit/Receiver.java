package com.zou.mqdemo.commit;

import com.zou.mqdemo.config.RabbitConfigure;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Zouwx
 * @create 2020-04-05 12:33
 * @desc 接收者
 **/
@Component
public class Receiver {

    @RabbitListener(queues = RabbitConfigure.SIMPLE_QUEUE)
    public void handler(String message){
        System.out.println("简单队列1：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.WORK_QUEUE)
    public void workReceiver1(String message){
        System.out.println("工作队列1：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.WORK_QUEUE)
    public void workReceiver2(String message){
        System.out.println("工作队列2：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.FANOUT_QUEUE)
    public void fanoutReceiver1(String message){
        System.out.println("广播队列1：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.FANOUT_QUEUE2)
    public void fanoutReceiver2(String message){
        System.out.println("广播队列2：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.DIRECT_QUEUE1)
    public void directReceiver1(String message){
        System.out.println("路由队列1：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.DIRECT_QUEUE2)
    public void directReceiver2(String message){
        System.out.println("路由队列2：接收消息："+message);
    }

    @RabbitListener(queues = RabbitConfigure.TOPIC_QUEUE1)
    public void topicReceiver1(String message){
        System.out.println("主题队列1：接收消息："+message);
    }
    @RabbitListener(queues = RabbitConfigure.TOPIC_QUEUE2)
    public void topicReceiver2(String message){
        System.out.println("主题队列2：接收消息："+message);
    }
}
