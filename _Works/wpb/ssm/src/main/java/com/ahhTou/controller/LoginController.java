package com.ahhTou.controller;

import com.ahhTou.bean.User;
import com.ahhTou.dao.LoginMapper;
import com.ahhTou.service.LoginService;
import com.ahhTou.untils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Resource
    LoginService loginService;

    @RequestMapping("/check")
    @ResponseBody
    public String checkLogin(@RequestBody User reqUser) {

        System.out.println("\r\n> 请求登录 username为" + reqUser.getUsername());
        try {
            User databaseUser = loginService.loginCheck(reqUser);
            System.out.println("> 验证成功, 设置token");
            String token = Token.create(databaseUser.getUsername());
            databaseUser.setToken(token);
            loginService.setToken(databaseUser);
            System.out.println("> 生成成功，准备返回值");
            return token;

        } catch (Exception e) {
            System.out.println("> 出现异常，可能是密码错误");
            return null;
        }

    }

}
