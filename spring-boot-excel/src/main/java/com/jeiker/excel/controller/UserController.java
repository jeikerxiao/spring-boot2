package com.jeiker.excel.controller;

import com.jeiker.excel.model.ExcelData;
import com.jeiker.excel.util.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/17 8:14 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("用户信息数据");
        //添加表头
        List<String> titles = new ArrayList();
        //for(String title: excelInfo.getNames())
        titles.add("ID");
        titles.add("年龄");
        titles.add("姓名");
        data.setTitles(titles);
        //添加列
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        for (int i = 1; i < 100; i++) {
            row = new ArrayList();
            row.add(i);
            row.add("28");
            row.add("xiao");
            rows.add(row);
        }
        data.setRows(rows);
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = fdate.format(new Date()) + ".xls";
        ExcelUtils.exportExcel(response, fileName, data);
    }
}
