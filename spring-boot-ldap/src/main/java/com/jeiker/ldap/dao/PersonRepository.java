package com.jeiker.ldap.dao;

import com.jeiker.ldap.model.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/11 2:05 PM
 */
public interface PersonRepository extends CrudRepository<Person, Name> {

}
