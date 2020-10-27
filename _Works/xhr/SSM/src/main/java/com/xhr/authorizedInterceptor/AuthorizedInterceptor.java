package com.xhr.authorizedInterceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        System.out.println("\r\n> 拦截到了" + req.getRequestURI() + "的请求");
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            System.out.println("> 未找到Cookies!");
            return false;
        }
        HttpSession session = req.getSession();
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    if (req.getSession().getId().equals(cookie.getValue())) {
                        if (req.getSession().getAttribute("username") != null) {
                            if (req.getSession().getAttribute("password") != null) {
                                cookie.setValue(session.getId());
                                cookie.setPath("/");
                                cookie.setMaxAge(500);
                                res.addCookie(cookie);
                                System.out.println("> 通过了免密码验证");
                                return true;
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("> 验证出错");
            e.printStackTrace();
            return false;
        }

        if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            String message = "{\"exit\":true}";
            res.setContentType("application/json;charset=UTF-8");
            res.getOutputStream().write(message.getBytes());
            System.out.println("> 是ajax拦截了");
        } else {
            String message = "{\"exit\":true}";
            res.setContentType("application/json;charset=UTF-8");
            res.getOutputStream().write(message.getBytes());
            System.out.println("> 并非Ajax其他拦截, 拒绝了请求");
        }
        System.out.println("> 拒绝了请求 + \r\n");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
