package com.jeiker.retry.controller;

import com.jeiker.retry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/10 2:58 PM
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/call")
    public Map<String, String> call() {
        try {
            userService.call();
        } catch (Exception e) {
            log.error("call userService error: {}", e);
        }
        return Collections.singletonMap("message", "hello");
    }
}
