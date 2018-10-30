package com.jeiker.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
//        new SpringApplicationBuilder(DubboProviderApplication.class)
//                .web(false) // 非 Web 应用
//                .run(args);
    }
}
