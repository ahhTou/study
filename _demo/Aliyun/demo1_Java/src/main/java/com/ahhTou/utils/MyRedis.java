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


    public RedisTemplate<String, String> setForString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return redisTemplate;
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


    public MyRedis set4String(String k, String v) {
        redisTemplate.opsForValue().set(k, v);
        return this;
        // 设置过期时间
    }

    public void expire(String k, int timer) {
        redisTemplate.expire(k, timer, TimeUnit.MINUTES);
    }

    public Object get(String k) {
        return redisTemplate.opsForValue().get(k);
    }

}
