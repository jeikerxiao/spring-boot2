package com.jeiker.jdbc.service.impl;

import com.jeiker.jdbc.model.User;
import com.jeiker.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        //SQL
        String sql = "INSERT INTO user (name,age) VALUES (?,?);";
        //执行写入
        int row = jdbcTemplate.update(sql, user.getName(), user.getAge());
        return row > 0;
    }

    @Override
    public List<User> getAllUser() {
        //SQL
        String sql = "SELECT *  FROM user";
        //结果
        //映射每行数据
        List<User> list = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setAge(resultSet.getInt("age"));
            user.setName(resultSet.getString("name"));
            user.setCreateTime(resultSet.getDate("create_time"));
            return user;
        });

        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public User getUser(Long userId) {
        String sql = "SELECT * FROM user WHERE id = ?";

        List<User> userList = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper(User.class));
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";

        List<User> userList = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper(User.class));
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        String sql = "DELETE FROM  user WHERE id = ?";
        int row = jdbcTemplate.update(sql, userId);
        return row > 0;
    }

    @Override
    public Boolean updateUser(User user) {
        //SQL
        String sql = "update user set name=?,age=? where id=?";
        //结果
        //映射数据
        int row = jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setLong(3, user.getId());
        });
        return row > 0;
    }
}
