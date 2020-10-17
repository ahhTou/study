package com.ahhtou.controller;

import com.ahhtou.bean.Board;
import com.ahhtou.utils.RedisUtils;
import com.ahhtou.utils.json.ObjectMapperUtils;
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
    RedisUtils redisUtils;

    @Resource
    ObjectMapperUtils objectMapperUtils;

    final String listName = "MsgBoardList";


    @PostMapping("/getAll")
    public List<String> getAllMsg() {
        return redisUtils.getAllList(listName);
    }

    @PostMapping("/setOne")
    public Boolean setOneMsg(@RequestBody Board msgBoard) {
        System.out.println(msgBoard);
        String s = objectMapperUtils.obj2String(msgBoard);
        redisUtils.lPushForString(listName, s);
        System.out.println("添加了");
        return true;

    }


}
