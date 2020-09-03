package com.ahhTou.controller;

import com.ahhTou.bean.User;
import com.ahhTou.service.MailService;
import com.ahhTou.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    MailService mailService;

    @RequestMapping("sendVerificationEmail")
    public Object sendVerificationEmail(@RequestBody String email) {
        try {
            // 检查邮箱是否唯一
            userService.checkEmailUnique(email);
            // 发送邮件
            new Thread(() -> mailService.sendVCode(email)).start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }

    @RequestMapping("checkVerification")
    public Object checkVerification(@RequestBody User req) {
        System.out.println("> 验证验证码是否正确");
        return mailService.checkVCode(req.getEmail(), req.getCode());
    }

    @RequestMapping("checkUsernameUnique")
    public Object checkUsernameUnique(@RequestBody String username) {
        System.out.println("> 验证用户名是否唯一");
        return userService.checkUsernameUnique(username);
    }

    @RequestMapping("register")
    public Object register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }

    @RequestMapping("regSuc")
    public Object regSuc(@RequestBody String username) {
        try {
            return userService.getUserMsgByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }


}
