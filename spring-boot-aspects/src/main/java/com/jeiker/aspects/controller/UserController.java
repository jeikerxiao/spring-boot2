package com.jeiker.aspects.controller;

import com.jeiker.aspects.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户控制器
 * User: jeikerxiao
 * Date: 2019/1/9 2:31 PM
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    /**
     * 查询所有用户
     */
    @GetMapping("")
    public Map<String, Object> getAllUser() {
        List<User> userList = new ArrayList<>();
        log.info("getAllUser() - 方法体执行");
        return Collections.singletonMap("result", userList);
    }

    @GetMapping("/error")
    public Object error() {
        log.info("error() - 方法体执行");
        return 1 / 0;
    }

}
