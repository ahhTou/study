package com.ahhTou.servlet;

import com.ahhTou.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 得到Session
        HttpSession session = req.getSession();

        // 给Session设置东西
        session.setAttribute("name", "阿偷ProMax");
        session.setAttribute("pojo", new Person("ahhTou", "20"));

        // 获取Session的ID
        String id = session.getId();

        // 判断是不是信的Session
        boolean aNew = session.isNew();
        if (aNew) {
            resp.getWriter().write("<h1>session创建成功</h1>");
        } else {
            resp.getWriter().write("<h1>已存在" + id + "</h1>");
        }

        // Session创建的时候做了什么事情
        resp.addCookie(new Cookie("JSESSIONID", id));
    }
}
