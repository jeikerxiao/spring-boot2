package com.jeiker.cache.service;

import com.jeiker.cache.model.User;

import java.util.List;

/**
 * Description: 用户 Service
 * User: jeikerxiao
 * Date: 2019/1/9 2:29 PM
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param user
     */
    User saveUser(User user);

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

    /**
     * 清空缓存
     */
    void deleteCache();

}