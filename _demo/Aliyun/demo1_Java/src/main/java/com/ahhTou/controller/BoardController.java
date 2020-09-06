package com.ahhTou.controller;

import com.ahhTou.bean.Board;
import com.ahhTou.utils.MyRedis;
import com.ahhTou.utils.MyObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/msgBoard")
public class BoardController {

    @Resource
    MyRedis myRedis;

    @Resource
    MyObjectMapper myObjectMapper;

    final String listName = "MsgBoardList";


    @PostMapping("/getAll")
    public List<String> getAllMsg() {
        return myRedis.getAllList(listName);
    }

    @PostMapping("/setOne")
    public Boolean setOneMsg(@RequestBody Board msgBoard) {
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
