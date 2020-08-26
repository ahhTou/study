package com.ahhTou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Location", "/r/img");
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); //        resp.setStatus(302);
        resp.sendRedirect("/img");
    }
}
