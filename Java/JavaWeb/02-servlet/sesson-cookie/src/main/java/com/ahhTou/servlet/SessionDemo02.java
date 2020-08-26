package com.ahhTou.servlet;

import com.ahhTou.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // 解决乱码问题
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");

            // 得到Session
            HttpSession session = req.getSession();

            String name = (String) session.getAttribute("name");
            Person pojo = (Person) session.getAttribute("pojo");


            String s = String.valueOf(pojo);
            resp.getWriter().write(name + s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
