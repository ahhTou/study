package com.xhr.controller;


import com.xhr.bean.User;
import com.xhr.bean.UserBaseData;
import com.xhr.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/getUserBaseDataByUsername")
    public @ResponseBody
    UserBaseData getUserBaseDataByUsername(@RequestBody String username) {
        String a = "root";
        UserBaseData userBaseDataByUsername = userMapper.getUserBaseDataByUsername(a);
        System.out.println(userBaseDataByUsername);
        return userBaseDataByUsername;
    }


    @RequestMapping("/checkLoginByUsernameAndPassword")
    public @ResponseBody
    Boolean checkLoginByUsernameAndPassword(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        UserBaseData userBaseData = userMapper.checkLoginByUsernameAndPassword(user.getUsername(), user.getPassword());


        System.out.println(userBaseData);

        if (userBaseData != null) {

            // 设置session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());

            // 设置cookie
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setValue(session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(500);
                    response.addCookie(cookie);
                }
            }
            return true;
        } else {
            return false;

        }


    }


}
