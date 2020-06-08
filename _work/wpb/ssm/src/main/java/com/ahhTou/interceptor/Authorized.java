package com.ahhTou.interceptor;

import com.ahhTou.untils.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Authorized implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("\r\n> 拦截到请求" + request.getRequestURI());
        if (request.getRequestURI().equals("/anime/getAll/")) {
            return true;
        } else {

            String token = request.getHeader("token");

            String tokenKey = Token.verify(token);

            return tokenKey != null && !tokenKey.equals("");
        }

    }
}
