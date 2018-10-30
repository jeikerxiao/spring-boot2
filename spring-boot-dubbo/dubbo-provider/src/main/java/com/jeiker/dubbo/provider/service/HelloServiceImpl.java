package com.jeiker.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jeiker.dubbo.api.HelloService;

/**
 * Description: spring-boot-dubbo
 * User: jeikerxiao
 * Date: 2018/10/30 下午1:48
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot Dubbo Provider)";
    }
}
