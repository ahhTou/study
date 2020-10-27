package com.ahhtou.nosql.service;

public interface MailRedis {

    String setVCode(String email, String code, Integer timer);

    String getVCode(String email);

    Boolean checkVCode(String email, String code);

}
