package com.xhr.authorizedInterceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                if (cookie.getValue().equals(session.getId())) {
                    System.out.println("通过验证");
                    cookie.setValue(session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(500);
                    response.addCookie(cookie);
                    return true;
                } else {
                    System.out.println("清除Cookie");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return false;
                }
            }else {
                System.out.println("No Session");
                return false;
            }
        }
        return true;
    }
}
