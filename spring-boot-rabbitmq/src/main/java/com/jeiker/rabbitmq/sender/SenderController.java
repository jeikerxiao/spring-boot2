package com.jeiker.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description: 消息生产者
 * User: jeikerxiao
 * Date: 2018/12/11 1:40 PM
 */
public class SenderController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/sendss")
    public void send1() {
        amqpTemplate.convertAndSend("exchange", "topic.messages", "hello topic.messages RabbitMQ");
    }

}
