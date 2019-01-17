package com.jeiker.excel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/17 7:53 PM
 */
@Data
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

    // 表头
    private List<String> titles;

    // 数据
    private List<List<Object>> rows;

    // 页签名称
    private String name;

}
