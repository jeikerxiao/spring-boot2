package com.jeiker.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description: 定时任务
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
     * fixedRate：指定两次任务执行的时间间隔(毫秒)，
     * 此时间间隔指的是，前一个任务开始与下一个任务开始的间隔
     */
    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("==>{} 使用fixedRate。", System.currentTimeMillis());
    }

    /**
     * fixedDelay：指定两次任务执行的时间间隔(毫秒)，
     * 此时间间隔指的是，前一次任务结束与下一个任务开始的间隔
     */
    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("==>{} 使用fixedDelay。", System.currentTimeMillis());
    }
}