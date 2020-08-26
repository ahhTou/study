package com.ahhTou.service;

public interface RedisService {

    String setVCode(String email, String code, Integer timer);

    String getVCode(String email);

}
