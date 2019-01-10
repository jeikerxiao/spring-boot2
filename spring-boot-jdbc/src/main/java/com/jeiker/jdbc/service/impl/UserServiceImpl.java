package com.jeiker.jdbc.service.impl;

import com.jeiker.jdbc.model.User;
import com.jeiker.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 用户 Service 实现
 * User: jeikerxiao
 * Date: 2019/1/9 2:29 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean saveUser(User user) {
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public User getUserByName(String name) {

        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return true;
    }

}
