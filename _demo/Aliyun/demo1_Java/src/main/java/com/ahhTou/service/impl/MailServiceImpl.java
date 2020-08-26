package com.ahhTou.service.impl;

import com.ahhTou.service.MailService;
import com.ahhTou.service.RedisService;
import com.ahhTou.utils.MyEmailSender;
import com.ahhTou.utils.MyTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Resource
    MyEmailSender myEmailSender;

    @Resource
    RedisService redisService;


    @Override
    public boolean sendVCode(String receiver) {
        String code = MyTools.createVerificationCode();
        String content = "您的注册验证码为:  <strong>" + code + "</strong><br>验证码在5分钟内有效，请在五分钟内完成登录";
        String title = "欢迎注册，无名小站";
        try {

            MimeMessage message = myEmailSender.createMimeMessage(receiver, content, title);
            myEmailSender.send(message);
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
