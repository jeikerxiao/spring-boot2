package com.jeiker.async.task;

import com.jeiker.async.model.AsyncVo;
import com.jeiker.async.queue.RequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 处理订单接口的任务，每个任务类处理一种接口
 * User: jeikerxiao
 * Date: 2019-08-19 08:07
 */
@Component
public class OrderTask extends Thread {

    @Autowired
    private RequestQueue queue;

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                AsyncVo<String, Object> vo = queue.getOrderQueue().take();
                System.out.println("[ OrderTask ]开始处理订单");

                String params = vo.getParams();
                // 执行3秒
                Thread.sleep(3000);
                Map<String, Object> map = new HashMap<>();
                map.put("params", params);
                map.put("time", System.currentTimeMillis());

                vo.getResult().setResult(map);

                System.out.println("[ OrderTask ]订单处理完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }

        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
