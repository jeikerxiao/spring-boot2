package com.jeiker.async2.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Description: spring-boot-async2
 * User: jeikerxiao
 * Date: 2019-08-19 08:54
 */
@Component
public class AsyncTask {


    @Async
    public Future<String> task1() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时：" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task1执行完毕");
    }

    @Async
    public Future<String> task2() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时：" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task2执行完毕");

    }

    @Async
    public Future<String> task3() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时：" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<>("task3执行完毕");

    }
}
