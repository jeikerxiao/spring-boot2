package com.jeiker.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2018/12/17 4:41 PM
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/log")
    public Map<String, String> testLog() {

        log.debug("debug log.");
        log.info("info log.");
        log.warn("warn log.");
        log.error("error log.");

        return Collections.singletonMap("message", "test logback");
    }
}
