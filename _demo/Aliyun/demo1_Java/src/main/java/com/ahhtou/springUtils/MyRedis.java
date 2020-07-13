package com.ahhtou.springUtils;

import com.ahhtou.utils.MyObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyRedis {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    MyObjectMapper myObjectMapper;

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

}
