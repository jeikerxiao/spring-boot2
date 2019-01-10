package com.jeiker.hsqldb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description: 用户
 * User: jeikerxiao
 * Date: 2019/1/9 2:21 PM
 */
@Entity
@Data
@NoArgsConstructor  // 定义无参构造器是因为JPA需要
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 主键自增长
    private Long id;
    private Integer age;
    private String name;

}
