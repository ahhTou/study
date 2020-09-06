package com.ahhtou.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/user/add")
    public String add() {
        return "add";
    }

    @GetMapping("/user/update")
    public String update() {
        return "update";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/yes/*")
    public String yes() {
        return "yes";
    }

    @GetMapping("/no/*")
    public String no() {
        return "no";
    }

    @GetMapping("/toLogin")
    public String toLogin(String username, String pwd) {
        try {
            // 获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);

            try {
                subject.login(token);
                return "Login Success";
            } catch (UnknownAccountException e) {
                return "Username Err";
            } catch (IncorrectCredentialsException e) {
                return "Pwd Err";
            }

        } catch (Exception e) {
            return "Login Err";
        }
    }

    @GetMapping("/noAuth")
    public String noAuth() {
        return "未授权";
    }

}
