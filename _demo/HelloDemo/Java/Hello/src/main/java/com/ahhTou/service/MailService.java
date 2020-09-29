package com.ahhTou.service;

public interface MailService {

    boolean sendVCode(String receiver);

    boolean checkVCode(String email, String code);

}
