package com.ahhtou;

import com.ahhtou.bean.MsgBoard;
import com.ahhtou.springUtils.MyBoard;
import com.ahhtou.springUtils.MyRedis;
import com.ahhtou.utils.MyObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class HelloApplicationTests {

    @Resource
    MyRedis myRedis;

    MyObjectMapper myObjectMapper = new MyObjectMapper();

    @Test
    void contextLoads() {
        Date date = new Date();
        MsgBoard msgBoard = new MsgBoard();
        msgBoard.setContent("u");
        msgBoard.setSendTime(date);
        msgBoard.setSendName("ahhTou");

        String s = myObjectMapper.obj2String(msgBoard);

        for (int i = 0; i < 1; i++) {
            myRedis.lPushForString("MsgBoardList", s);

        }

/*        List<String> list = myRedis.getAllList("MsgBoardList");
        System.out.println(list);*/

/*        String s = myObjectMapper.obj2String(msgBoard);
        System.out.println(s);

        myRedis.setForString("msg1", s);*/
    }

}
