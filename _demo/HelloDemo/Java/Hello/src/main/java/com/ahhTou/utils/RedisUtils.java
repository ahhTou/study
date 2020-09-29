package com.ahhTou.utils;

import com.ahhTou.bean.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

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


    public RedisUtils set(String k, String v) {
        redisTemplate.opsForValue().set(k, v);
        return this;
    }

    public RedisUtils set(String k, Set<String> v) {
        v.forEach(a -> redisTemplate.opsForSet().add(k, a));
        return this;
    }

    public RedisUtils set(String k, String... v) {
        for (String s : v) redisTemplate.opsForSet().add(k, s);
        return this;
    }


    public void expire(String k, int timer) {
        redisTemplate.expire(k, timer, TimeUnit.MINUTES);
    }

    public Object getString(String k) {
        return redisTemplate.opsForValue().get(k);
    }

    public Set<String> getSet(String k) {
        return redisTemplate.opsForSet().members(k);
    }

}
