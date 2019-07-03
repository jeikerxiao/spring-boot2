package com.jeiker.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot-swagger
 * User: jeikerxiao
 * Date: 2019/7/3 11:06 AM
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    private Map<String, String> hello() {
        return Collections.singletonMap("message", "hello");
    }
}
