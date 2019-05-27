package com.jeiker.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot-war
 * User: jeikerxiao
 * Date: 2018/11/1 上午9:52
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public Map<String, String> hi() {
        return Collections.singletonMap("message", "This Spring Boot Web");
    }
}
