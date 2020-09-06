package com.ahhTou.controller;

import com.ahhTou.bean.User;
import com.ahhTou.service.MailService;
import com.ahhTou.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    MailService mailService;

    @PostMapping("sendVerificationEmail")
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

    @PostMapping("checkVerification")
    public Object checkVerification(@RequestBody User req) {
        System.out.println("> 验证验证码是否正确");
        return mailService.checkVCode(req.getEmail(), req.getCode());
    }

    @PostMapping("checkUsernameUnique")
    public Object checkUsernameUnique(@RequestBody String username) {
        System.out.println("> 验证用户名是否唯一");
        return userService.checkUsernameUnique(username);
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }

    @PostMapping("regSuc")
    public Object regSuc(@RequestBody String username) {
        try {
            return userService.getUserMsgByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }


}
