package com.jeiker.async2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootAsync2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsync2Application.class, args);
    }

}
