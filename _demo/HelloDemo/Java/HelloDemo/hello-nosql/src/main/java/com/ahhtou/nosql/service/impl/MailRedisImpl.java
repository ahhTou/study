package com.ahhtou.nosql.service.impl;

import com.ahhtou.common.utils.CommonConstant;
import com.ahhtou.common.utils.SimpleTools;
import com.ahhtou.nosql.config.RedisUtils;
import com.ahhtou.nosql.service.MailRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailRedisImpl implements MailRedis {

    @Resource
    RedisUtils redisUtils;

    @Override
    public String setVCode(String email, String code, Integer timer) {
        try {
            String key = CommonConstant.MAIL_V_CODE + email;
            redisUtils.set(key, code).expire(key, 5);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getVCode(String email) {
        try {
            String key = CommonConstant.MAIL_V_CODE + email;
            return (String) redisUtils.getString(key);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean checkVCode(String email, String code) {

        String rCode = this.getVCode(email);

        if (rCode != null && !rCode.equals(""))

            return rCode.equals(code);

        else
            return false;
    }
}
