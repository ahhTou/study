package com.ahhtou;

import com.ahhtou.utils.EmailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
class HelloEmailApplicationTests {

    @Resource
    EmailUtils emailUtils;

    @Test
    void contextLoads() throws MessagingException {
        emailUtils.sHtml("1197977498@qq.com", "测试", "你好");
    }

}

