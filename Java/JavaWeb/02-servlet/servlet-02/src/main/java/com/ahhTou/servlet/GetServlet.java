package com.ahhTou.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
        ServletContext servletContext = this.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        PrintWriter writer = resp.getWriter();
        writer.print(username);
    }
}
