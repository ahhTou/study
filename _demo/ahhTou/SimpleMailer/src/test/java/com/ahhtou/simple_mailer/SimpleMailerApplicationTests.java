package com.ahhtou.simple_mailer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SimpleMailerApplicationTests {

    @Resource
    SimpleMailer simpleMailer;

    @Test
    void contextLoads() {
        System.out.println(simpleMailer.sendVerificationCodeEmail("1197977498@qq.com"));
    }

}
