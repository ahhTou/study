package com.ahhTou;

import com.ahhTou.utils.jwt.JwtUtils;
import com.ahhTou.utils.var.CommonConstant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() {
        String username = "Test";
        String pwd = "pwd";
        String token = JwtUtils.sign(username, pwd);
        try {
            JwtUtils.verify(token, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
