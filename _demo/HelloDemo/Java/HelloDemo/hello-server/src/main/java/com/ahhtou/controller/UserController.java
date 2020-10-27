package com.ahhtou.controller;

import com.ahhtou.common.pojo.HelloResult;
import com.ahhtou.common.pojo.User;
import com.ahhtou.common.utils.CommonConstant;
import com.ahhtou.nosql.service.AuthRedis;
import com.ahhtou.nosql.service.MailRedis;
import com.ahhtou.service.UserService;

import com.ahhtou.service.VCodeEmailService;
import com.ahhtou.utils.ShiroUtils;
import com.ahhtou.common.utils.SimpleTools;
import com.ahhtou.common.pojo.ReturnException;
import com.ahhtou.common.utils.jwt.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    VCodeEmailService vCodeEmailService;

    @Resource
    MailRedis mailRedis;

    @Resource
    AuthRedis authRedis;

    @PostMapping("sendVerificationEmail")
    public HelloResult sendVerificationEmail(@RequestBody String email) throws ReturnException, MessagingException {
        // 检查邮箱是否唯一
        Boolean aBoolean = userService.checkEmailUnique(email);
        if (!aBoolean) throw new ReturnException("该账户已被注册", 400);

        // 发送邮件, 并返回验证码
        String code = vCodeEmailService.sendVCode(email);

        // 保存code
        mailRedis.setVCode(email, code, 5);

        return new HelloResult("验证码发送成功");
    }

    @PostMapping("checkVerification")
    public HelloResult checkVerification(@RequestBody User req) throws ReturnException {
        System.out.println("> 验证验证码是否正确");
        boolean b = mailRedis.checkVCode(req.getEmail(), req.getCode());
        if (!b) throw new ReturnException("验证码不正确", 400);
        return new HelloResult("验证码正确");
    }

    @PostMapping("checkUsernameUnique")
    public HelloResult checkUsernameUnique(@RequestBody String username) throws ReturnException {
        System.out.println("> 验证用户名是否唯一");
        Boolean aBoolean = userService.checkUsernameUnique(username);
        if (!aBoolean) throw new ReturnException("用户名不唯一", 400);
        return new HelloResult("用户名唯一");
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) throws ReturnException {
        // md5 后端加盐
        String newPwd = SimpleTools.getPwdByMd5(user);
        user.setPassword(newPwd);


        Boolean register = userService.register(user);

        if (!register) throw new ReturnException("注册失败", 400);
        return new HelloResult("注册成功");
    }

    @PostMapping("regSuc")
    public HelloResult regSuc(@RequestBody String username) throws ReturnException {


        User userMsgByUsername = userService.getUserMsgByUsername(username);
        return new HelloResult(userMsgByUsername, "密码验证成功");
    }

    @PostMapping("toLogin")
    public HelloResult toLogin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws ReturnException {
        System.out.println(request.getHeader(CommonConstant.ACCESS_TOKEN));
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
        return new HelloResult(token, "密码核对成功");

    }


}
