package com.jeiker.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description: 消息消费者
 * User: jeikerxiao
 * Date: 2018/12/11 1:41 PM
 */
@Component
public class Receiver {

    @RabbitListener(queues = "topic.messages")
    public void process2(String str1) throws ClassNotFoundException {
        System.out.println("messages ：" + str1);
        System.out.println(Thread.currentThread().getName() + "接收到来自topic.message队列的消息: " + str1);
    }
}