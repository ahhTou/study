package com.ahhTou.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MyRedis {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void setForString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getForString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void lPushForString(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public List<String> getAllList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public String setEmailVerificationCode(String email, String code, Integer timer) {
        try {
            String key = "VerificationCodeFor_" + email;
            redisTemplate.opsForValue().set(key, code);
            redisTemplate.expire(key, timer, TimeUnit.MINUTES);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEmailVerificationCode(String email) {
        try {
            String key = "VerificationCodeFor_" + email;
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
