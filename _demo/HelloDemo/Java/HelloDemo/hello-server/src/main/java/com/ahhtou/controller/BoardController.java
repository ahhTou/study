package com.ahhtou.controller;

import com.ahhtou.common.pojo.Board;
import com.ahhtou.common.utils.OmUtils;
import com.ahhtou.common.utils.SimpleTools;
import com.ahhtou.nosql.config.RedisUtils;
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
    OmUtils omUtils;

    final String listName = "MsgBoardList";


    @PostMapping("/getAll")
    public List<String> getAllMsg() {
        return redisUtils.getAllList(listName);
    }

    @PostMapping("/setOne")
    public Boolean setOneMsg(@RequestBody Board msgBoard) {

        String s = omUtils.obj2String(msgBoard);
        redisUtils.lPushForString(listName, s);

        return true;

    }


}
