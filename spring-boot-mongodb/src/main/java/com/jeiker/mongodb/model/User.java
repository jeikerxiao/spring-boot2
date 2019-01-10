package com.jeiker.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 用户
 * User: jeikerxiao
 * Date: 2019/1/9 2:21 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;

    private Long id;
    private Integer age;
    private String name;
    private Date createTime;

}
