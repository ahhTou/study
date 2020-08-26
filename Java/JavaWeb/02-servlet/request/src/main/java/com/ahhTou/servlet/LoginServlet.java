package com.ahhTou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        System.out.println(Arrays.toString(req.getParameterValues("hobby")));
        System.out.println(req.getContextPath());

        resp.setCharacterEncoding("utf-8");
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }
}
