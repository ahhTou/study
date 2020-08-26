package com.ahhTou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServletDemo05 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo5");
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        InputStream is2 = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/ahhTou/servlet/db.properties");

        Properties properties = new Properties();
        Properties properties2 = new Properties();
        properties.load(is);
        String u = properties.getProperty("username");
        String password = properties.getProperty("password");
        properties2.load(is2);
        String u1 = properties2.getProperty("username");
        String p2 = properties2.getProperty("password");
        resp.getWriter().append(u).append("===").append(password).append("===").append(u1).append("===").append(p2);

    }
}
