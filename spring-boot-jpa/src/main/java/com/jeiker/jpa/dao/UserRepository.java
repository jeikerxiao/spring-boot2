package com.jeiker.jpa.dao;

import com.jeiker.jpa.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Description: UserRepository 接口操作User对象
 * User: jeikerxiao
 * Date: 2019/1/9 2:26 PM
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);
}
