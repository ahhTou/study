package com.ahhTou.controller;

import com.ahhTou.bean.User;
import com.ahhTou.service.UserService;

import com.ahhTou.utils.MyTools;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    MyTools myTools;

    @RequestMapping("sendVerificationEmail")
    public Object sendVerificationEmail(@RequestBody String email) {
        try {
            // 检查邮箱是否唯一
            if (userService.checkEmailUnique(email)) {
                // 发送邮件
                new Thread(() -> myTools.sendVCodeUnique(email)).start();
                return true;
            }
        } catch (Exception e) {
            return "err";
        }
        return false;

    }

    @RequestMapping("checkVerification")
    public Object checkVerification(@RequestBody User req) {
        System.out.println("> 验证验证码是否正确");
        return myTools.checkVCode(req.getEmail(), req.getCode());
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
