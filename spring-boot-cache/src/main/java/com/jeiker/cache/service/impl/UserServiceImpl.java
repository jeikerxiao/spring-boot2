package com.jeiker.cache.service.impl;

import com.google.common.collect.Lists;
import com.jeiker.cache.dao.UserRepository;
import com.jeiker.cache.model.User;
import com.jeiker.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 用户 Service 实现
 * User: jeikerxiao
 * Date: 2019/1/9 2:29 PM
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @CachePut(value = "user", key = "#user.name")
    public User saveUser(User user) {
        userRepository.save(user);
        User saveUser = getUser(user.getId());
        return saveUser;
    }

    @Override
    public List<User> getAllUser() {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> userList = Lists.newArrayList(userIterable);
        return userList;
    }

    @Override
    @Cacheable(value = "user", key = "#userId")
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    @Cacheable(value = "user", key = "#name")
    public User getUserByName(String name) {
        List<User> userList = userRepository.findByName(name);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    @CacheEvict(value = "user", key = "#userId")
    public Boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void deleteCache() {

    }

}
