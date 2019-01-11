package com.jeiker.cache.service.impl;

import com.google.common.collect.Lists;
import com.jeiker.cache.dao.UserRepository;
import com.jeiker.cache.model.User;
import com.jeiker.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;

    @Override
    public Boolean saveUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getAllUser() {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> userList = Lists.newArrayList(userIterable);
        return userList;
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        List<User> userList = userRepository.findByName(name);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

}
