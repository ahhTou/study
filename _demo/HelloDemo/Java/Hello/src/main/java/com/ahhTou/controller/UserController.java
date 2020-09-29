package com.ahhTou.controller;

import com.ahhTou.bean.Result;
import com.ahhTou.bean.User;
import com.ahhTou.redis.AuthRedis;
import com.ahhTou.service.MailService;
import com.ahhTou.service.RedisService;
import com.ahhTou.service.UserService;

import com.ahhTou.utils.ShiroUtils;
import com.ahhTou.utils.var.CommonConstant;
import com.ahhTou.exception.ReturnException;
import com.ahhTou.utils.jwt.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    MailService mailService;

    @Resource
    RedisService redisService;

    @Resource
    AuthRedis authRedis;

    @PostMapping("sendVerificationEmail")
    public Object sendVerificationEmail(@RequestBody String email) {
        // 检查邮箱是否唯一
        userService.checkEmailUnique(email);
        // 发送邮件
        new Thread(() -> mailService.sendVCode(email)).start();
        return true;
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
        return userService.register(user);
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

    @PostMapping("toLogin")
    public Result toLogin(@RequestBody User user, HttpServletResponse response) throws ReturnException {

        // 得到用户
        User resUser = userService.loginAndBackUser(user);

        // 新建 Token
        String token = JwtUtils.sign(resUser.getUsername(), resUser.getPassword());

        // 保存 服务端用户登陆Token
        authRedis.setAuthToken(token);

        // 授权 添加基础member
        Subject currentUser = SecurityUtils.getSubject();
        if (!ShiroUtils.isMember(currentUser)) {
            authRedis.setPermission(ShiroUtils.SHIRO_ROLES_MEMBER, resUser.getUsername());
        }


        // 设置 用户Token到响应头
        response.setHeader(CommonConstant.ACCESS_TOKEN, token);

        return new Result("密码核对成功");

    }


}
