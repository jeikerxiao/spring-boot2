package com.jeiker.pdf.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jeiker.pdf.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 下载pdf文档
 * User: jeikerxiao
 * Date: 2019/1/17 8:39 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 下载pdf文档
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        List<User> list = new ArrayList<>();
        list.add(new User(1L, 23, "xiao"));
        list.add(new User(2L, 24, "xiao"));
        list.add(new User(3L, 25, "xiao"));
        list.add(new User(4L, 26, "xiao"));
        list.add(new User(5L, 27, "xiao"));
        for (User user : list) {

            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getId().toString()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getAge().toString()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getName()));
            table.addCell(cell);
            document.add(table);

        }
        document.close();
    }

}
