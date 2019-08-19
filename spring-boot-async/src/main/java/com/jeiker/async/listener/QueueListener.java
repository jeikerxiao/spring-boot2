package com.jeiker.async.listener;

import com.jeiker.async.task.OrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description: 队列监听器，初始化启动所有监听任务
 * User: jeikerxiao
 * Date: 2019-08-19 08:06
 */
@Component
public class QueueListener {

    @Autowired
    private OrderTask orderTask;

    /**
     * 初始化时启动监听请求队列
     */
    @PostConstruct
    public void init() {
        orderTask.start();
    }

    /**
     * 销毁容器时停止监听任务
     */
    @PreDestroy
    public void destory() {
        orderTask.setRunning(false);
    }
}
