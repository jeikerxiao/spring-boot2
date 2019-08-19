package com.jeiker.async2.controller;

import com.jeiker.async2.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * Description: 异步任务，总时间按最长时间来算
 * User: jeikerxiao
 * Date: 2019-08-19 08:51
 */
@RequestMapping("")
@RestController
public class AsyncTaskController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("")
    public String doTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();
        Future<String> task3 = asyncTask.task3();
        String result = null;
        for (; ; ) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都完成，退出循环等待
                break;
            }
            Thread.sleep(1000);

        }
        long currentTimeMillis1 = System.currentTimeMillis();
        result = "task 任务总耗时：" + (currentTimeMillis1 - currentTimeMillis) + "ms";
        return result;
    }
}
