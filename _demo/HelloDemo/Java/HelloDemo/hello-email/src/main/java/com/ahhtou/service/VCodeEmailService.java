package com.ahhtou.service;

import javax.mail.MessagingException;

public interface VCodeEmailService {

    String sendVCode(String email) throws MessagingException;

}
