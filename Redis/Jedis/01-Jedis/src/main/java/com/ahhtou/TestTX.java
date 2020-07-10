package com.ahhtou;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        Transaction multi = jedis.multi();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "ahhTou");

        String s = jsonObject.toJSONString();

        try {
            multi.set("user1", s);
            multi.set("user2", s);

            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();

        }


    }
}
