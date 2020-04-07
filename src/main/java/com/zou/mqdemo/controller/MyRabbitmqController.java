package com.zou.mqdemo.controller;

import com.zou.mqdemo.commit.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zouwx
 * @create 2020-04-05 12:34
 * @desc
 **/
@Controller
@RequestMapping("/rabbitmq")
public class MyRabbitmqController {

    @Autowired
    Sender sender;

    /**
     * @Description: 简单队列
     * @param:
     * @Auther: Zouwx
     * @Date: 2020/4/7 15:21
     * @return:
     */
    @RequestMapping("/sender")
    @ResponseBody
    public String sender(){
        System.out.println("send string:hello world");
        sender.send("hello world");
        return "sending...";
    }

    /**
     * @Description: 一对多工作队列
     * @param:
     * @Auther: Zouwx
     * @Date: 2020/4/7 15:21
     * @return:
     */
    @RequestMapping("/worker")
    @ResponseBody
    public String worker(){
        System.out.println("send string:hello world");
        for (int i = 0 ; i<=10 ; i++ ){
            sender.worker("发送第"+i+"条消息");
        }
        return "sending...";
    }

    /**
     * @Description: 发布/订阅 fanout模式
     * @param:
     * @Auther: Zouwx
     * @Date: 2020/4/7 15:21
     * @return:
     */
    @RequestMapping("/fanout")
    @ResponseBody
    public String fanout(){
        System.out.println("send string:hello world ");
        for (int i = 0 ; i<=5 ; i++ ){
            sender.fanout("发送第"+i+"条消息");
        }
        return "sending...";
    }

    /**
     * @Description: 发布/订阅 direct
     * @param:
     * @Auther: Zouwx
     * @Date: 2020/4/7 15:21
     * @return:
     */
    @RequestMapping("/direct1")
    @ResponseBody
    public String direct1(){
        System.out.println("send string:hello world ");
        for (int i = 0 ; i<=2 ; i++ ){
            sender.direct1("发送第"+i+"条消息");
        }
        return "sending...";
    }
    @RequestMapping("/direct2")
    @ResponseBody
    public String direct2(){
        System.out.println("send string:hello world ");
        for (int i = 0 ; i<=2 ; i++ ){
            sender.direct2("发送第"+i+"条消息");
        }
        return "sending...";
    }

    /**
     * @Description: 发布/订阅 主题模式
     * @param:
     * @Auther: Zouwx
     * @Date: 2020/4/7 15:21
     * @return:
     */
    @RequestMapping("/topic1")
    @ResponseBody
    public String topic1(){
        System.out.println("send string:hello world ");
        for (int i = 0 ; i<=2 ; i++ ){
            sender.topic1("topic1发送第"+i+"条消息");
            sender.topic2("topic2发送第"+i+"条消息");
        }
        return "sending...";
    }

    @RequestMapping("/topic2")
    @ResponseBody
    public String topic2(){
        System.out.println("send string:hello world ");
        for (int i = 0 ; i<=2 ; i++ ){
            sender.topic3("topic3发送第"+i+"条消息");
            sender.topic4("topic4发送第"+i+"条消息");
        }
        return "sending...";
    }
}
