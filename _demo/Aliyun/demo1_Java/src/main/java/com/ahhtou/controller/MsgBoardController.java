package com.ahhtou.controller;

import com.ahhtou.bean.MsgBoard;
import com.ahhtou.springUtils.MyRedis;
import com.ahhtou.utils.MyObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msgBoard")
public class MsgBoardController {

    @Resource
    MyRedis myRedis;

    MyObjectMapper myObjectMapper = new MyObjectMapper();

    final String listName = "MsgBoardList";


    @RequestMapping("/getAll")
    public List<String> getAllMsg() {
        return myRedis.getAllList(listName);
    }

    @RequestMapping("/setOne")
    public Boolean setOneMsg(@RequestBody MsgBoard msgBoard) {
        try {
            System.out.println(msgBoard);
            String s = myObjectMapper.obj2String(msgBoard);
            myRedis.lPushForString(listName, s);
            System.out.println("添加了");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
