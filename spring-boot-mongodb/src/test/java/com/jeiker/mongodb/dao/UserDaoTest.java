package com.jeiker.mongodb.dao;

import com.jeiker.mongodb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/10 3:46 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(2L);
        user.setName("xiao");
        user.setAge(12);
        userDao.saveUser(user);
    }

    @Test
    public void findUserByName() {
        User user = userDao.findUserByName("xiao");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2L);
        user.setName("jeiker");
        user.setAge(21);
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(2L);
    }
}