package com.kuang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws JsonProcessingException {
        User user = new User("ahhTou啊", 3);
        String s = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void contextLoads() {

        // redisTemplate
        // opsForValue 操作字符串 类似String
        // opsForList 操作List 类似List
        // opsFrSet
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog


        // 除了基本的操作，我们常用的方法都可以直接redisTempLate操作

        // 获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

/*        redisTemplate.opsForValue().set("myKey","ahhTou");
        System.out.println(redisTemplate.opsForValue().get("myKey"));*/


    }

}
