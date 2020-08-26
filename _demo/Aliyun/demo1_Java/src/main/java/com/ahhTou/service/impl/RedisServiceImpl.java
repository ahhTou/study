package com.ahhTou.service.impl;

import com.ahhTou.service.RedisService;
import com.ahhTou.utils.MyRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    MyRedis myRedis;


    @Override
    public String setVCode(String email, String code, Integer timer) {
        try {
            String key = "VerificationCodeFor_" + email;
            myRedis.set4String(key, code).expire(key, 5);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getVCode(String email) {
        try {
            String key = "VerificationCodeFor_" + email;
            return (String) myRedis.get(key);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
