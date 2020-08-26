package com.ahhTou.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");

        System.out.println("执行前");
        filterChain.doFilter(req, res);     // 让我请求继续走，否则就被拦截
        System.out.println("执行后");

    }

    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("👴初始化辣");

    }

    public void destroy() {

        System.out.println("👴被销毁了辣");
    }
}
