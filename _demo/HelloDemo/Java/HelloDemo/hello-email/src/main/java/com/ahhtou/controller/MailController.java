package com.ahhtou.controller;

import com.ahhtou.common.pojo.HelloResult;
import com.ahhtou.common.pojo.User;
import com.ahhtou.nosql.service.MailRedis;
import com.ahhtou.utils.EmailUtils;
import com.ahhtou.common.utils.SimpleTools;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
@RequestMapping("/account/mail")
public class MailController {

    @Resource
    EmailUtils emailUtils;

    @Resource
    MailRedis mailRedis;

    @PostMapping("/sendVcEmail")
    public HelloResult sendVcCode(@RequestBody User user) throws MessagingException {

        String code = SimpleTools.createVerificationCode();
        String content = "您的注册验证码为:  <strong>" + code + "</strong><br>验证码在5分钟内有效，请在五分钟内完成登录";
        String subject = "欢迎注册，无名小站";

        emailUtils.sHtml(user.getEmail(), subject, content);

        mailRedis.setVCode(user.getEmail(), code, 5);

        return new HelloResult("邮件发送成功");
    }
}
