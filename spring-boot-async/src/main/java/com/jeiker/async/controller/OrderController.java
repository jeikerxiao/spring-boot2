package com.jeiker.async.controller;

import com.jeiker.async.model.AsyncVo;
import com.jeiker.async.queue.RequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Description: spring-boot-async
 * User: jeikerxiao
 * Date: 2019-08-19 08:11
 */
@RestController
public class OrderController {

    @Autowired
    private RequestQueue queue;

    @GetMapping("/order")
    public DeferredResult<Object> order(String number) throws InterruptedException {
        System.out.println("[ OrderController ] 接到下单请求");
        System.out.println("当前待处理订单数： " + queue.getOrderQueue().size());

        AsyncVo<String, Object> vo = new AsyncVo<>();
        DeferredResult<Object> result = new DeferredResult<>();

        vo.setParams(number);
        vo.setResult(result);

        queue.getOrderQueue().put(vo);
        System.out.println("[ OrderController ] 返回下单结果");
        return result;
    }

}
