package com.ahhTou.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        this.getInitParameter();      初始化参数
//        this.getServletConfig();      Servlet 配置
//        this.getServletContext();     Servlet 上下文
        System.out.println("Hello");
        ServletContext servletContext = this.getServletContext(); // 上下文
        servletContext.setAttribute("username", "ahhTou");

        PrintWriter writer = resp.getWriter();
        writer.print("HelloServlet02");
    }
}
