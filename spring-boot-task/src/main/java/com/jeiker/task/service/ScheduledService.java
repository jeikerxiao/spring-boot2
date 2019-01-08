package com.jeiker.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/8 5:20 PM
 */
@Slf4j
@Component
public class ScheduledService {

    /**
     * cron：通过表达式来配置任务执行时间
     */
    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info("==>{} 使用cron。", System.currentTimeMillis());
    }

    /**
     * fixedRate：定义一个按一定频率执行的定时任务
     */
    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("==>{} 使用fixedRate。", System.currentTimeMillis());
    }

    /**
     * fixedDelay：定义一个按一定频率执行的定时任务，
     * 与上面不同的是，改属性可以配合 initialDelay， 定义该任务延迟执行时间。
     */
    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("==>{} 使用fixedDelay。", System.currentTimeMillis());
    }
}