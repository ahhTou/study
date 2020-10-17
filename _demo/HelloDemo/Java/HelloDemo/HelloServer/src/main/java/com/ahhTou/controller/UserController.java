package com.ahhtou.controller;

import com.ahhtou.bean.Result;
import com.ahhtou.bean.User;
import com.ahhtou.redis.AuthRedis;
import com.ahhtou.service.MailService;
import com.ahhtou.service.UserService;

import com.ahhtou.utils.ShiroUtils;
import com.ahhtou.utils.SimpleTools;
import com.ahhtou.utils.CommonConstant;
import com.ahhtou.exception.ReturnException;
import com.ahhtou.utils.jwt.JwtUtils;
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
    AuthRedis authRedis;

    @PostMapping("sendVerificationEmail")
    public Result sendVerificationEmail(@RequestBody String email) throws ReturnException {
        // 检查邮箱是否唯一
        Boolean aBoolean = userService.checkEmailUnique(email);
        if (!aBoolean) throw new ReturnException("邮箱不唯一", 400);

        // 发送邮件
        mailService.sendVCode(email);
        return new Result("验证码发送成功");
    }

    @PostMapping("checkVerification")
    public Result checkVerification(@RequestBody User req) throws ReturnException {
        System.out.println("> 验证验证码是否正确");
        boolean b = mailService.checkVCode(req.getEmail(), req.getCode());
        if (!b) throw new ReturnException("验证码不正确", 400);
        return new Result("验证码正确");
    }

    @PostMapping("checkUsernameUnique")
    public Result checkUsernameUnique(@RequestBody String username) throws ReturnException {
        System.out.println("> 验证用户名是否唯一");
        Boolean aBoolean = userService.checkUsernameUnique(username);
        if (!aBoolean) throw new ReturnException("用户名不唯一", 400);
        return new Result("用户名唯一");
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) throws ReturnException {
        System.out.println("一次加密的密码" + user.getPassword());
        System.out.println("二次加密的密码" + SimpleTools.getPwdByMd5(user));

        // md5 后端加盐
        String newPwd = SimpleTools.getPwdByMd5(user);
        user.setPassword(newPwd);


        Boolean register = userService.register(user);

        if (!register) throw new ReturnException("注册失败", 400);
        return new Result("注册成功");
    }

    @PostMapping("regSuc")
    public Result regSuc(@RequestBody String username) throws ReturnException {


        User userMsgByUsername = userService.getUserMsgByUsername(username);
        return new Result(userMsgByUsername);
    }

    @PostMapping("toLogin")
    public Result toLogin(@RequestBody User user, HttpServletResponse response) throws ReturnException {

        System.out.println("一次加密的密码" + user.getPassword());
        System.out.println("二次加密的密码" + SimpleTools.getPwdByMd5(user));

        // 将密码设置为加密密码
        user.setPassword(SimpleTools.getPwdByMd5(user));

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
