package com.jeiker.aspects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description: 用户
 * User: jeikerxiao
 * Date: 2019/1/9 2:21 PM
 */
@Data
@NoArgsConstructor  // 定义无参构造器是因为JPA需要
@AllArgsConstructor
public class User {

    private Long id;
    private Integer age;
    private String name;
    private Date createTime;

}
