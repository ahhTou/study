package com.ahhTou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

// 保存用户上一次访问的时间
public class CookieDemo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进来了");
        req.setCharacterEncoding("gbk");
        resp.setCharacterEncoding("gbk");

        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            out.print("你上一次访问的时间是");
            for (Cookie cookie : cookies) {
                // 得到cookie的名字
                String name = cookie.getName();
                if (name.equals("lastLoginTime")) {
                    // 获取cookie里的值
                    long l = Long.parseLong(cookie.getValue());
                    out.append(String.valueOf(new Date(l)));

                }
            }
        } else {
            out.println("您访问了个🐥巴");
        }
        Cookie c1 = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        c1.setMaxAge(24 * 60 * 60);
        resp.addCookie(c1);

    }
}
