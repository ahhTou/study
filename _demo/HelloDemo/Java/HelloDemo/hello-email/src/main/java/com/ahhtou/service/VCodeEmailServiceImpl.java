package com.ahhtou.service;

import com.ahhtou.common.utils.SimpleTools;
import com.ahhtou.nosql.service.MailRedis;
import com.ahhtou.utils.EmailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Service
public class VCodeEmailServiceImpl implements VCodeEmailService {

    @Resource
    EmailUtils emailUtils;

    @Resource
    MailRedis mailRedis;


    @Override
    public String sendVCode(String email) throws MessagingException {
        String code = SimpleTools.createVerificationCode();
        String content = "您的注册验证码为:  <strong>" + code + "</strong><br>验证码在5分钟内有效，请在五分钟内完成登录";
        String subject = "欢迎注册，无名小站";

        emailUtils.sHtml(email, subject, content);

        return code;
    }
}
