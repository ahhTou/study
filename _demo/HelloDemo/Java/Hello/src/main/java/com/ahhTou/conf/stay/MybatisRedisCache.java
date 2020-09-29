package com.ahhTou.conf.stay;

import com.ahhTou.utils.spring.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MybatisRedisCache implements Cache {

    // log
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final RedisTemplate<String, Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");


    private final String id;


    public void log(String out) {
        final String LOG_HEADER = "Mybatis:Redis-Cache => ";
        System.out.println(LOG_HEADER + out);
    }

    public MybatisRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log("Put");
        if (value != null) {
            // 向Redis中添加数据，有效时间是2天
            redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        log("Get");

        try {
            if (key != null) {
                return redisTemplate.opsForValue().get(key.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        log("Remove");

        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() {
        log("Clear");

        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        log("GetSize");

        Long size = redisTemplate.execute(RedisServerCommands::dbSize);
        assert size != null;
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        log("getLock");
        return this.readWriteLock;
    }
}

