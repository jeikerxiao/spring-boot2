package com.jeiker.encrypt.dao;

import com.jeiker.encrypt.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Description: spring-boot-encrypt
 * User: jeikerxiao
 * Date: 2019/7/19 2:04 PM
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);
}