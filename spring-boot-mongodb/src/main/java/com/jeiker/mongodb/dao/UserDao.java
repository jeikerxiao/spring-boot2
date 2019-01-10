package com.jeiker.mongodb.dao;

import com.jeiker.mongodb.model.User;

/**
 * Description: 用户Dao
 * User: jeikerxiao
 * Date: 2019/1/10 3:40 PM
 */
public interface UserDao {

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据名字查找用户
     *
     * @param userName
     * @return
     */
    User findUserByName(String userName);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(Long id);
}
