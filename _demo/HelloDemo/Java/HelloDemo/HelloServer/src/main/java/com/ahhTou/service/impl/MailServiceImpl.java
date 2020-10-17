package com.ahhtou.service.impl;

import com.ahhtou.service.MailService;
import com.ahhtou.service.RedisService;
import com.ahhtou.utils.EmailSenderUtils;
import com.ahhtou.utils.SimpleTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Resource
    EmailSenderUtils emailSenderUtils;

    @Resource
    RedisService redisService;


    @Override
    public boolean sendVCode(String receiver) {
        String code = SimpleTools.createVerificationCode();
        String content = "您的注册验证码为:  <strong>" + code + "</strong><br>验证码在5分钟内有效，请在五分钟内完成登录";
        String title = "欢迎注册，无名小站";
        try {

            MimeMessage message = emailSenderUtils.createMimeMessage(receiver, content, title);
            emailSenderUtils.send(message);
            return redisService.setVCode(receiver, code, 5) == null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkVCode(String email, String code) {

        String rCode = redisService.getVCode(email);
        if (rCode != null && !rCode.equals(""))

            return rCode.equals(code);

        else
            return false;

    }
}
