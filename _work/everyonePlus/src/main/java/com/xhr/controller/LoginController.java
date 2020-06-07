package com.xhr.controller;

import com.xhr.bean.User;
import com.xhr.bean.UserBaseData;
import com.xhr.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("LoginController")
@RequestMapping("/login")
public class LoginController {

    LoginMapper loginMapper;

    @Autowired
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @RequestMapping("/exit")
    @ResponseBody
    public Boolean exitLogin(@RequestBody String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("\n\r" + "> root用户退出请求");
        if (key.equals("del")) {
            System.out.println("> 收到一个正确的退出登录请求");
            Cookie[] cookies = request.getCookies();
            System.out.println("> 销毁了Session");
            request.getSession().invalidate();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setValue("null");
                    cookie.setPath("/");
                    cookie.setMaxAge(-100);
                    response.addCookie(cookie);
                    System.out.println("> 销毁Cookie");
                    break;
                }
            }
            return true;
        } else {
            System.out.println("> 收到了一个错误的退出登录请求" + "\n\r");
            return false;
        }
    }


    @RequestMapping("/check")
    public @ResponseBody
    Boolean checkLoginByUsernameAndPassword(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("\r\n> " + user.getUsername() + "请求登录");
        UserBaseData userBaseData = null;
        try {
            userBaseData = loginMapper.checkLoginByUsernameAndPassword(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            System.out.println("> 查询出现异常");
            e.printStackTrace();
        }
        if (userBaseData != null) {
            // 设置session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());

            if (session.isNew()) {
                System.out.println("> 新建了Session");
            } else {
                System.out.println("> 发现了旧的Session");
            }
            // 设置cookie
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        cookie.setValue(session.getId());
                        cookie.setPath("/");
                        cookie.setMaxAge(500);
                        response.addCookie(cookie);
                        System.out.println("> 新建了Cookie");
                    }
                }
            }else {
                System.out.println("> 没有发现cookie");
                Cookie cookie = new Cookie("JSESSIONID", session.getId());
                cookie.setValue(session.getId());
                cookie.setPath("/");
                cookie.setMaxAge(500);
                response.addCookie(cookie);
                System.out.println("> 新建了Cookie");
            }

            System.out.println("> 通过登录");
            return true;
        }

        System.out.println("> 登录失败");
        return false;
    }


}
