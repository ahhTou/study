package com.ahhTou;

import com.ahhTou.bean.User;
import com.ahhTou.mapper.UserMapper;
import com.ahhTou.service.BaseService;
import com.ahhTou.service.UserService;
import com.ahhTou.utils.img.ImgSearcher;
import com.ahhTou.utils.MyRedis;
import com.ahhTou.utils.MyEmailSender;
import com.ahhTou.utils.MyObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HelloApplicationTests {


    @Resource
    MyRedis myRedis;

    @Resource
    MyObjectMapper myObjectMapper;

    @Resource
    MyEmailSender myEmailSender;


    @Resource
    UserMapper userMapper;

    @Resource
    UserService userService;

    @Resource
    ImgSearcher imgSearcher;


    @Resource
    BaseService baseService;

    @Test
    void bg() {
        List<String> bgImages = baseService.getBgImages();
    }

    @Test
    void t1() {
        User user = new User();
        user.setEmail("xx@qq.com");
        user.setUsername("ahhTouPro");
        user.setPassword("xw3333");
        String s = myObjectMapper.obj2String(user);
        System.out.println(s);
    }

    @Test
    void testMybatis() {

/*        System.out.println("=====");
        System.out.println(Thread.currentThread().getName());*/
//        System.out.println(userMapper.getUserMsgByUsername("aaa"));
//        System.out.println(myRedis.getAllList("ss"));
        User user = new User();
        user.setEmail("xx@qq.com");
        user.setUsername("ahhTouPro");
        user.setPassword("xw3333");
        userMapper.register(user);

/*        System.out.println(userService.checkUsernameUnique("123123"));
        System.out.println(userService.checkEmailUnique("0@qq.com"));*/
    }

    @Test
    void testEmail() throws ClassNotFoundException {

/*        String email = "1197977498@qq.com";

        for (int i = 0; i < 10; i++) {
            String b = myEmailSender.sendVerificationCodeEmail(email);
            if (b != null && !b.equals("")) {
                System.out.println(b);
                System.out.println("发送成功");
            }
        }*/
//        String s = myRedis.setEmailVerificationCode("xxx", "123456", 5);
//        String emailVerificationCode = myRedis.getEmailVerificationCode(email);
//        System.out.println(emailVerificationCode);
//        System.out.println("=====" + s + "========" + emailVerificationCode);

    }

    @Test
    void contextLoads() {
/*        Date date = new Date();
        Board msgBoard = new Board();
        msgBoard.setContent("u");
        msgBoard.setSendTime(date);
        msgBoard.setSendName("ahhTou");

        String s = myObjectMapper.obj2String(msgBoard);

        for (int i = 0; i < 1; i++) {
            myRedis.lPushForString("MsgBoardList", s);

        }*/

/*        List<String> list = myRedis.getAllList("MsgBoardList");
        System.out.println(list);*/

/*        String s = myObjectMapper.obj2String(msgBoard);
        System.out.println(s);

        myRedis.setForString("msg1", s);*/
    }

}
