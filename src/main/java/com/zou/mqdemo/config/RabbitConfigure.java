package com.zou.mqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zouwx
 * @create 2020-04-05 12:26
 * @desc 消息队列配置类
 **/
@Configuration
public class RabbitConfigure {

    public static final String SIMPLE_QUEUE = "simple.queue";
    //Work模式一个生产者对应多个消费者
    public static final String WORK_QUEUE = "work.queue";
    //订阅模型-Fanout
    public static final String FANOUT_QUEUE = "fanout.queue";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    //订阅模式-direct
    public static final String DIRECT_QUEUE1 = "direct.queue1";
    public static final String DIRECT_QUEUE2 = "direct.queue2";
    //主题模式-Topic
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";


    @Bean
    @Qualifier(SIMPLE_QUEUE)
    public Queue directQueue(){
        //队列名字，是否持久化
        return new Queue(SIMPLE_QUEUE,false);
    }

    @Bean
    @Qualifier(WORK_QUEUE)
    public Queue workQueue(){
        //队列名字，是否持久化
        return new Queue(WORK_QUEUE,false);
    }

    @Bean
    @Qualifier(FANOUT_QUEUE)
    public Queue fanoutQueue(){
        //队列名字，是否持久化
        return new Queue(FANOUT_QUEUE,false);
    }

    @Bean
    @Qualifier(FANOUT_QUEUE2)
    public Queue fanoutQueue2(){
        //队列名字，是否持久化
        return new Queue(FANOUT_QUEUE2,false);
    }

    @Bean
    @Qualifier(DIRECT_QUEUE1)
    public Queue directQueue1(){
        //队列名字，是否持久化
        return new Queue(DIRECT_QUEUE1,false);
    }

    @Bean
    @Qualifier(DIRECT_QUEUE2)
    public Queue directQueue2(){
        //队列名字，是否持久化
        return new Queue(DIRECT_QUEUE2,false);
    }
    @Bean
    @Qualifier(TOPIC_QUEUE1)
    public Queue topicQueue1(){
        //队列名字，是否持久化
        return new Queue(TOPIC_QUEUE1,false);
    }
    @Bean
    @Qualifier(TOPIC_QUEUE2)
    public Queue topicQueue2(){
        //队列名字，是否持久化
        return new Queue(TOPIC_QUEUE2,false);
    }


    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    /**
     * 声明一个Fanout类型的交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
    /**
     * 声明一个Direct类型的交换机
     * @return
     */
    @Bean
    public DirectExchange directExchanges(){
        //交换器名称、是否持久化、是否自动删除
        return new DirectExchange("directExchange",false,false);
    }
    /**
     * 声明一个topic类型的交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        //交换器名称、是否持久化、是否自动删除
        return new TopicExchange("topicExchange",false,false);
    }


    /**
     * 绑定fanoutQueue队列到交换机
     * @param fanoutQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    /**
     * 绑定fanoutQueue2队列到交换机
     * @param fanoutQueue2
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeB(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    /**
     * 绑定directQueue1队列到交换机
     * @param directQueue1
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingDirectExchangeA(Queue directQueue1, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("directTopic1");
    }

    /**
     * 绑定directQueue2队列到交换机
     * @param directQueue2
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingDirectExchangeB(Queue directQueue2, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("directTopic2");
    }

    /**
     * #：匹配一个或多个词	person.#：能够匹配person.insert.save 或者 person.insert
     * *：匹配不多不少恰好1个词	human.*：只能匹配human.insert
     */
    /**
     * 绑定topicExchange队列到交换机
     * @param topicQueue1
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingTopicExchangeA(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("human.*");
    }

    /**
     * 绑定topicExchange队列到交换机
     * @param topicQueue2
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingTopicExchangeB(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("person.#");
    }

}

