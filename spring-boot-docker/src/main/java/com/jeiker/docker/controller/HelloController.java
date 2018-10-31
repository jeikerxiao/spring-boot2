package com.jeiker.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot-docker
 * User: jeikerxiao
 * Date: 2018/10/30 下午6:37
 */
@RestController
public class HelloController {

    @RequestMapping("")
    public Map<String, String> hi() {
        return Collections.singletonMap("message", "Hi!");
    }

    @RequestMapping("/hello")
    public Map<String, String> hello() {
        return Collections.singletonMap("message", "Hello World!");
    }
}
