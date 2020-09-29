package com.ahhTou.service.impl;

import com.ahhTou.service.RedisService;
import com.ahhTou.utils.RedisUtils;
import com.ahhTou.utils.var.CommonConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

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
}
