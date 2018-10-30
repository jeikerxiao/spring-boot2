package com.jeiker.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jeiker.dubbo.api.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: spring-boot-dubbo
 * User: jeikerxiao
 * Date: 2018/10/30 下午1:54
 */
@RestController
public class HelloController {

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return helloService.sayHello(name);
    }
}
