package com.ahhTou.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyTools {

    @Resource
    MyRedis myRedis;

    @Resource
    MyEmailSender myEmailSender;


    public Boolean checkVCode(String email, String code) {

        String rCode = myRedis.getEmailVerificationCode(email);
        if (rCode != null && !rCode.equals(""))

            return rCode.equals(code);

        else
            return false;

    }

    public Boolean sendVCodeUnique(String email) {
        String s = myEmailSender.sendVerificationCodeEmail(email);
        return s != null && !s.equals("");
    }
}
