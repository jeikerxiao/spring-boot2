package com.jeiker.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Description: 用户
 * User: jeikerxiao
 * Date: 2019/1/9 2:21 PM
 */
@Entity
@Data
@NoArgsConstructor  // 定义无参构造器是因为JPA需要
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增长
    private Long id;
    private Integer age;
    private String name;
    @Column(name = "create_time")
    private Date createTime;

}
