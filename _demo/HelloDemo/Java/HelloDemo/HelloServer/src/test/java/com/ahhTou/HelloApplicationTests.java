package com.ahhtou;

import com.ahhtou.bean.User;
import com.ahhtou.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HelloApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("我");
        user.setPassword("x");
        user.setEmail("886@qq.com");
        userMapper.register(user);
/*        String username = "Test";
        String pwd = "pwd";
        String token = JwtUtils.sign(username, pwd);
        try {
            JwtUtils.verify(token, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
