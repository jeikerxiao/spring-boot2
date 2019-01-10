package com.jeiker.actuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/10 4:07 PM
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, String> user() {
        return Collections.singletonMap("message", "Hello");
    }
}
