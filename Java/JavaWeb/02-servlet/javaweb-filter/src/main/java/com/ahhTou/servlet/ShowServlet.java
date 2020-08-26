package com.ahhTou.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.write("我是你Servlet~~");

        ServletContext ctx = req.getServletContext();

        writer.append("/r/n");

        System.out.println(ctx.getAttribute("OnlineCount"));

        writer.append("当前访问人叔").append(String.valueOf(ctx.getAttribute("OnlineCount")));


    }
}
