package com.jeiker.encrypt.controller;

import com.jeiker.encrypt.model.User;
import com.jeiker.encrypt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description: spring-boot-encrypt
 * User: jeikerxiao
 * Date: 2019/7/19 1:45 PM
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     */
    @PostMapping("")
    public Map<String, Object> save(@RequestBody User user) {
        Boolean result = userService.saveUser(user);
        return Collections.singletonMap("result", result);
    }

    /**
     * 查询所有用户
     */
    @GetMapping("")
    public Map<String, Object> getAllUser() {
        List<User> userList = userService.getAllUser();
        return Collections.singletonMap("result", userList);
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/id/{userId}")
    public Map<String, Object> getUser(@PathVariable("userId") Long userId) {
        User user = userService.getUser(userId);
        return Collections.singletonMap("result", user);
    }

    /**
     * 根据用户名查询用户
     */
    @GetMapping("/name/{userName}")
    public Map<String, Object> getUserByName(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        return Collections.singletonMap("result", user);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    public Map<String, Object> deleteUser(@PathVariable("userId") Long userId) {
        Boolean result = userService.deleteUser(userId);
        return Collections.singletonMap("result", result);
    }
}