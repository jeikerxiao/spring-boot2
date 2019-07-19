package com.jeiker.encrypt.service;

import com.jeiker.encrypt.model.User;

import java.util.List;

/**
 * Description: spring-boot-encrypt
 * User: jeikerxiao
 * Date: 2019/7/19 2:05 PM
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     */
    Boolean saveUser(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    User getUser(Long userId);

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    Boolean deleteUser(Long userId);
}
